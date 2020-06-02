package cn.kd.dsr.modules.hotel.controller;

import cn.kd.dsr.common.controller.BaseController;
import cn.kd.dsr.common.dto.R;
import cn.kd.dsr.common.utils.DateUtils;
import cn.kd.dsr.common.utils.SessionTool;
import cn.kd.dsr.modules.hotel.entity.Guest;
import cn.kd.dsr.modules.hotel.entity.Logs;
import cn.kd.dsr.modules.hotel.entity.Reserve;
import cn.kd.dsr.modules.hotel.entity.Room;
import cn.kd.dsr.modules.hotel.service.GuestService;
import cn.kd.dsr.modules.hotel.service.LogsService;
import cn.kd.dsr.modules.hotel.service.ReserveService;
import cn.kd.dsr.modules.hotel.service.RoomService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 客房预订表 前端控制器
 * </p>
 * @since 2020-03-25 16:19:28
 */
@RestController
@RequestMapping("hotel/reserve")
public class ReserveController extends BaseController {

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private GuestService guestService;
    @Autowired
    private LogsService logsService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("hotel/reserve_room");
    }

    /**
     * 客房预订-（宾客自己预定）
     */
    @GetMapping(value = "outAddPage")
    public ModelAndView outAdd() {
        ModelAndView mv = new ModelAndView("hotel/reserve_room_out_add");
        List<Room> rooms = roomService.list(Wrappers.<Room>lambdaQuery().eq(Room::getStatus, 1));
        mv.addObject("rooms",rooms);
        return mv;
    }

    @PostMapping(value = "outAdd")
    public R outAdd(Reserve reserve) {
        // 校验预订入住时间与退房时间
        try {
            Date startDate = DateUtils.parseString2Date(reserve.getStartTime());
            Date endDate = DateUtils.parseString2Date(reserve.getEndTime());
            int days = DateUtils.dateDiff(endDate, startDate);
            if(days<=0){
                return R.fail("预订退房时间不能小于入住时间，最少住一天哦!");
            }
        }catch (ParseException ex){
            return R.fail("日期输入错误");
        }

        // 预订时输入房间号
        if(reserve.getRoomNum() != null && reserve.getRoomNum()!= ""){
            Room room = getRoomByRoomNum(reserve.getRoomNum());
            if(room != null){
                int room_status = room.getStatus();
                if(room_status == 0){
                    return R.fail("房间已被禁用，请更换房间");
                }else if(room_status == 2){
                    return R.fail("房间已有预订");
                }else{
                    reserve.setRoomId(room.getId());
                    reserve.setRoomType(room.getType());
                    // 修改此房间状态为有预订状态
                    Room room1 = new Room();
                    room1.setId(room.getId());
                    room1.setStatus(2);
                    roomService.updateById(room1);
                }
            }else{
                return R.fail("经查询无此房间");
            }
        }

        // 页面输入电话，查询数据库guest表，有此宾客信息就取出
        //     没有此宾客信息，先新建宾客信息（只有宾客姓名电话），其他身份信息等入住需补齐。
        Guest guestFromDatabase = guestService.getOne(Wrappers.<Guest>lambdaQuery().eq(Guest::getPhoneNum, reserve.getPhonenum()), false);
        if(null == guestFromDatabase){
            // 宾客表没有此宾客电话号码
            Guest guest = new Guest();
            guest.setPhoneNum(reserve.getPhonenum());
            guest.setName(reserve.getGuestName());
            guest.setCreateBy("admin");
            guest.setIdNum(Long.valueOf("0"));
            Integer guest_id = guestService.savaGuest(guest);
            reserve.setGuestId(guest_id);
        }else {
            reserve.setGuestId(guestFromDatabase.getId());
            reserve.setGuestName(guestFromDatabase.getName());
        }

        reserve.setCreateTime(null);
        reserve.setStatus(0);
        reserve.setCreateBy("宾客网上预定");
        reserveService.save(reserve);
        return R.ok();
    }

    @GetMapping(value = "look")
    public ModelAndView look(Long id) {
        ModelAndView mv = new ModelAndView("hotel/reserve_room_add");
        Reserve reserve = reserveService.getById(id);
        mv.addObject("editInfo", reserve);
        return mv;
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("hotel/reserve_room_edit");
        Reserve reserve;
        if (id == null) {
            reserve = new Reserve();
        } else {
            reserve = reserveService.getById(id);
        }
        mv.addObject("editInfo", reserve);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Reserve reserve) {
        Page<Reserve> page = reserveService.listRoomPage(reserve);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(Reserve reserve) {
        // 校验预订入住时间与退房时间
        try {
            Date startDate = DateUtils.parseString2Date(reserve.getStartTime());
            Date endDate = DateUtils.parseString2Date(reserve.getEndTime());
            int days = DateUtils.dateDiff(endDate, startDate);
            if(days<=0){
                return R.fail("预订退房时间不能小于入住时间，最少住一天哦!");
            }
        }catch (ParseException ex){
            return R.fail("日期输入错误");
        }

        // 预订时输入房间号
        if(reserve.getRoomNum() != null && reserve.getRoomNum()!= ""){
            Room room = getRoomByRoomNum(reserve.getRoomNum());
            if(room != null){
                int room_status = room.getStatus();
                if(room_status == 0){
                    return R.fail("房间已被禁用，请更换房间");
                }else if(room_status == 2){
                    return R.fail("房间已有预订");
                }else{
                    reserve.setRoomId(room.getId());
                    reserve.setRoomType(room.getType());
                    // 修改此房间状态为有预订状态
                    Room room1 = new Room();
                    room1.setId(room.getId());
                    room1.setStatus(2);
                    roomService.updateById(room1);
                }
            }else{
                return R.fail("经查询无此房间");
            }
        }

        // 页面输入电话，查询数据库guest表，有此宾客信息就取出
        //     没有此宾客信息，先新建宾客信息（只有宾客姓名电话），其他身份信息等入住需补齐。
        Guest guestFromDatabase = guestService.getOne(Wrappers.<Guest>lambdaQuery().eq(Guest::getPhoneNum, reserve.getPhonenum()), false);
        if(null == guestFromDatabase){
            // 宾客表没有此宾客电话号码
            Guest guest = new Guest();
            guest.setPhoneNum(reserve.getPhonenum());
            guest.setName(reserve.getGuestName());
            guest.setCreateBy("admin");
            guest.setIdNum(Long.valueOf("0"));
            Integer guest_id = guestService.savaGuest(guest);
            reserve.setGuestId(guest_id);
        }else {
            reserve.setGuestId(guestFromDatabase.getId());
            reserve.setGuestName(guestFromDatabase.getName());
        }

        reserve.setCreateTime(null);
        reserve.setStatus(0);
        reserve.setCreateBy(SessionTool.getUserName());
        reserveService.save(reserve);
        return R.ok();
    }

    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        reserveService.removeByIds(ids);
        return R.ok();
    }

    @PostMapping(value = "edit")
    public R edit(Reserve reserve) {
        // 校验预订入住时间与退房时间
        try {
            Date startDate = DateUtils.parseString2Date(reserve.getStartTime());
            Date endDate = DateUtils.parseString2Date(reserve.getEndTime());
            int days = DateUtils.dateDiff(endDate, startDate);
            if(days<=0){
                return R.fail("预订退房时间不能小于入住时间，最少住一天哦!");
            }
        }catch (ParseException ex){
            return R.fail("日期输入错误");
        }

        // 房间号有变动时，重新校验房间号
        Reserve byId = reserveService.getById(reserve.getId());
        if(!byId.getRoomNum().equals(reserve.getRoomNum())){
            // 校验房间号
            if(reserve.getRoomNum() != null && reserve.getRoomNum()!= ""){
                Room room = getRoomByRoomNum(reserve.getRoomNum());
                if(room != null){
                    int room_status = room.getStatus();
                    if(room_status == 0){
                        return R.fail("房间已被禁用，请更换房间");
                    }else if(room_status == 2){
                        return R.fail("房间已有预订");
                    }else{
                        reserve.setRoomId(room.getId());
                        reserve.setRoomType(room.getType());
                        // 修改此房间状态为有预订状态
                        Room room1 = new Room();
                        room1.setId(room.getId());
                        room1.setStatus(2);
                        roomService.updateById(room1);
                    }
                }else{
                    return R.fail("经查询无此房间");
                }

                reserve.setRoomId(room.getId());
                reserve.setRoomType(room.getType());
            }
        }
        if(null != byId.getRoomNum()){
            Room rooma = new Room();
            rooma.setId(byId.getId());
            rooma.setStatus(1);
            roomService.updateById(rooma);
        }

        reserveService.updateById(reserve);
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        reserveService.removeById(id);
        return R.ok();
    }

    /**
     * 退订
     * @param id
     * @return
     */
    @PostMapping(value = "exits/{id}")
    public R exits(@PathVariable Integer id) {
        Reserve reserve = new Reserve();
        reserve.setId(id);
        reserve.setStatus(3);
        reserveService.updateById(reserve);

        // 修改房间状态为空闲
        Reserve byId = reserveService.getById(id);
        // 预订时设置了房间号，
        if(null != byId.getRoomId()){
            Room room = new Room();
            room.setId(byId.getRoomId());
            room.setStatus(1);
            roomService.updateById(room);
        }
        return R.ok();
    }

    /**
     * 入住
     * @param id
     * @return
     */
    @PostMapping(value = "pindown/{id}")
    public R pindown(@PathVariable Integer id) {
        Reserve reserve = reserveService.getById(id);
        Logs logs = new Logs();
        logs.setRoomId(reserve.getRoomId());
        logs.setRoomNum(reserve.getRoomNum());
        logs.setRoomType(reserve.getRoomType());
        logs.setGuestId(reserve.getGuestId());
        logs.setGuestName(reserve.getGuestName());
        logs.setPhonenum(reserve.getPhonenum());
        logs.setStartTime(reserve.getStartTime());
        logs.setEndTime(reserve.getEndTime());
        logs.setStatus(1);
        logs.setPay(reserve.getPay());
        logs.setPriceStatus(0);
        logs.setCreateTime(null);
        logs.setCreateBy(SessionTool.getUserName());
        try{
            // 计算两个时间段相隔天数
            Date start = DateUtils.parseString2Date(logs.getStartTime());
            Date end = DateUtils.parseString2Date(logs.getEndTime());
            int stay_day = DateUtils.dateDiff(end,start);
            Room room = roomService.getById(logs.getRoomId());
            // 房费计算
            BigDecimal price = new BigDecimal(room.getPrice().toString());
            price = price.multiply(new BigDecimal(String.valueOf(stay_day)));
            logs.setPrice(price);
        }catch (Exception e){
            System.out.println("房费计算失败");
            return R.fail("房费计算错误，请稍后重试 ");
        }
        logsService.save(logs);

        // 把状态改为 已入住
        reserve.setStatus(2);
        reserveService.updateById(reserve);

        // 房间状态改为 有人 入住
        Room room = new Room();
        room.setId(reserve.getRoomId());
        room.setStatus(3);
        roomService.updateById(room);

        return R.ok();
    }



    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(reserveService.getById(id));
    }

    public Room getRoomByRoomNum(String roomNum){
        return roomService.getOne(Wrappers.<Room>lambdaQuery().eq(Room::getRoomNum, roomNum), false);
    }
}

