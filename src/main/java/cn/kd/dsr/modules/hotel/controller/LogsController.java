package cn.kd.dsr.modules.hotel.controller;

import cn.kd.dsr.common.controller.BaseController;
import cn.kd.dsr.common.dto.R;
import cn.kd.dsr.common.utils.DateUtils;
import cn.kd.dsr.common.utils.SessionTool;
import cn.kd.dsr.modules.hotel.entity.Guest;
import cn.kd.dsr.modules.hotel.entity.Logs;
import cn.kd.dsr.modules.hotel.entity.Room;
import cn.kd.dsr.modules.hotel.service.GuestService;
import cn.kd.dsr.modules.hotel.service.LogsService;
import cn.kd.dsr.modules.hotel.service.RoomService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * <p>
 * 入住登记表 前端控制器
 * </p>
 * @since 2020-03-27 10:22:53
 */
@RestController
@RequestMapping("hotel/logs")
public class LogsController extends BaseController {

    @Autowired
    private LogsService logsService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private GuestService guestService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("hotel/logs");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("hotel/logs_edit");
        Logs logs;
        if (id == null) {
            logs = new Logs();
            List<Guest> guests = guestService.list();
            List<Guest> collect = guests.stream().sorted(Comparator.comparing(Guest::getCreateTime, Comparator.reverseOrder())).collect(toList());
            mv.addObject("guests",collect);
        } else {
            logs = logsService.getById(id);
        }
        List<Room> rooms = roomService.list(Wrappers.<Room>lambdaQuery().eq(Room::getStatus, 1));
        mv.addObject("rooms",rooms);
        mv.addObject("editInfo", logs);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Logs logs) {
        Page<Logs> page = logsService.listLogsPage(logs);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(Logs logs) {

        Room room = roomService.getOne(Wrappers.<Room>lambdaQuery().eq(Room::getRoomNum, logs.getRoomNum()),false);
        if(null != room){
            // check 房间状态
            if(room.getStatus() == 3){
                return R.fail("此房间已经有人");
            }else if(room.getStatus() == 2){
                return R.fail("此房间已有预订");
            }else if(room.getStatus() == 0){
                return R.fail("此房间已禁用");
            }
        }else{
            return R.fail("无此房间");
        }

        logs.setRoomId(room.getId());
        logs.setRoomType(room.getType());

        Guest guest = guestService.getOne(Wrappers.<Guest>lambdaQuery().eq(Guest::getPhoneNum, logs.getPhonenum()), false);
        if(null == guest){
            return R.fail("无此宾客信息");
        }
        logs.setGuestId(guest.getId());
        logs.setGuestName(guest.getName());

        try{
            // 计算两个时间段相隔天数
            Date start = DateUtils.parseString2Date(logs.getStartTime());
            Date end = DateUtils.parseString2Date(logs.getEndTime());
            int stay_day = DateUtils.dateDiff(end,start);
            if(stay_day <= 0){
                return R.fail("退房时间不能早于入住时间");
            }
            // 房费计算
            BigDecimal price = new BigDecimal(room.getPrice().toString());
            price = price.multiply(new BigDecimal(String.valueOf(stay_day)));
            logs.setPrice(price);
        }catch (Exception e){
            System.out.println("房费计算失败");
            return R.fail("房费计算错误，请稍后重试 ");
        }


        logs.setStatus(1);
        logs.setPriceStatus(0);
        logs.setCreateBy(SessionTool.getUserName());
        logs.setCreateTime(null);
        logsService.save(logs);

        // 修改房间状态
        room.setStatus(3);
        roomService.updateById(room);

        return R.ok();
    }

    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        logsService.removeByIds(ids);
        return R.ok();
    }

    @PostMapping(value = "edit")
    public R edit(Logs logs) {

        Logs logFromDB = logsService.getOne(Wrappers.<Logs>lambdaQuery().eq(Logs::getId, logs.getId()), false);
        int roomIdFromDB = logFromDB.getRoomId();



        Room room = roomService.getOne(Wrappers.<Room>lambdaQuery().eq(Room::getRoomNum, logs.getRoomNum()),false);

        if(room.getId() != roomIdFromDB){
            if(null != room){
                // check 房间状态
                if(room.getStatus() == 3){
                    return R.fail("此房间已经有人");
                }else if(room.getStatus() == 2){
                    return R.fail("此房间已有预订");
                }else if(room.getStatus() == 0){
                    return R.fail("此房间已禁用");
                }
            }else{
                return R.fail("无此房间");
            }
        }
        logs.setRoomId(room.getId());
        logs.setRoomType(room.getType());


        try{
            // 计算两个时间段相隔天数
            Date start = DateUtils.parseString2Date(logs.getStartTime());
            Date end = DateUtils.parseString2Date(logs.getEndTime());
            int stay_day = DateUtils.dateDiff(end,start);
            if(stay_day <= 0){
                return R.fail("退房时间不能早于入住时间");
            }
            // 房费计算
            BigDecimal price = new BigDecimal(room.getPrice().toString());
            price = price.multiply(new BigDecimal(String.valueOf(stay_day)));
            logs.setPrice(price);
        }catch (Exception e){
            System.out.println("房费计算失败");
            return R.fail("房费计算错误，请稍后重试 ");
        }

        logsService.updateById(logs);

        if(room.getId() != roomIdFromDB){
            // 修改原来的房间为空闲
            Room room2 = new Room();
            room2.setId(roomIdFromDB);
            room2.setStatus(1);
            roomService.updateById(room2);
            // 修改房间状态
            room.setStatus(3);
            roomService.updateById(room);
        }
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        logsService.removeById(id);
        return R.ok();
    }

    /**
     * 结账房费
     * @param id
     * @return
     */
    @PostMapping(value = "payment/{id}")
    public R payment(@PathVariable int id) {
        Logs logs = new Logs();
        logs.setId(id);
        logs.setPriceStatus(1);
        logsService.updateById(logs);
        return R.ok();
    }

    /**
     * 退房
     * @param id
     * @return
     */
    @PostMapping(value = "goAway/{id}/{roomId}")
    public R goAway(@PathVariable(value = "id") int id, @PathVariable(value = "roomId")int roomId) {
        Logs logs = new Logs();
        logs.setId(id);
        logs.setStatus(2);
        logsService.updateById(logs);

        // 修改房间状态为空闲
        Room room = new Room();
        room.setId(roomId);
        room.setStatus(1);
        roomService.updateById(room);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(logsService.getById(id));
    }


}

