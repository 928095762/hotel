package cn.kd.dsr.modules.hotel.controller;

import cn.kd.dsr.common.controller.BaseController;
import cn.kd.dsr.common.dto.R;
import cn.kd.dsr.common.utils.SessionTool;
import cn.kd.dsr.modules.hotel.entity.Guest;
import cn.kd.dsr.modules.hotel.service.GuestService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


/**
 * <p>
 * 宾客表 前端控制器
 * </p>
 * @since 2020-03-25 14:37:53
 */
@RestController
@RequestMapping("hotel/guest")
public class GuestController extends BaseController {

    @Autowired
    private GuestService guestService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("hotel/guest");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("hotel/guest_edit");
        Guest guest;
        if (id == null) {
            guest = new Guest();
        } else {
            guest = guestService.getById(id);
        }
        mv.addObject("editInfo", guest);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Guest guest) {
        Page<Guest> page = guestService.listGuestPage(guest);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(Guest guest) {
        guest.setCreateTime(null);
        guest.setCreateBy(SessionTool.getUserName());
        guestService.save(guest);
        return R.ok();
    }

    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        guestService.removeByIds(ids);
        return R.ok();
    }

    @PostMapping(value = "edit")
    public R edit(Guest guest) {
        guestService.updateById(guest);
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        guestService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(guestService.getById(id));
    }

}

