package cn.kd.dsr.modules.hotel.controller;

import cn.kd.dsr.common.controller.BaseController;
import cn.kd.dsr.common.dto.R;
import cn.kd.dsr.common.utils.SessionTool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import cn.kd.dsr.modules.hotel.entity.Room;
import cn.kd.dsr.modules.hotel.service.RoomService;

import java.util.List;


/**
 * <p>
 * 房间表 前端控制器
 * </p>
 * @since 2020-03-25 09:41:05
 * @Author zhen
 */
@RestController
@RequestMapping("hotel/room")
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("hotel/room");
    }

    @GetMapping("indexView")
    public ModelAndView indexView() {
        return new ModelAndView("hotel/room_view");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("hotel/room_edit");
        Room room;
        if (id == null) {
            room = new Room();
        } else {
            room = roomService.getById(id);
        }
        mv.addObject("editInfo", room);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Room room) {
        Page<Room> page = roomService.listRoomPage(room);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(Room room) {
        room.setCreateTime(null);
        room.setCreateBy(SessionTool.getUserName());
        roomService.save(room);
        return R.ok();
    }

    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        roomService.removeByIds(ids);
        return R.ok();
    }

    @PostMapping(value = "edit")
    public R edit(Room room) {
        roomService.updateById(room);
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        roomService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(roomService.getById(id));
    }

}

