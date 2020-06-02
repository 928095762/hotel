package cn.kd.dsr.modules.hotel.controller;

import cn.kd.dsr.common.controller.BaseController;
import cn.kd.dsr.common.dto.R;
import cn.kd.dsr.common.utils.SessionTool;
import cn.kd.dsr.modules.hotel.entity.User;
import cn.kd.dsr.modules.hotel.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 * @since 2020-04-04 10:21:51
 */
@RestController
@RequestMapping("sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("hotel/user");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("hotel/user_edit");
        User user;
        if (id == null) {
            user = new User();
        } else {
            user = userService.getById(id);
        }
        mv.addObject("editInfo", user);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(User user) {
        Page<User> page = userService.listUserlalalaPage(user);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(User user) {
        user.setCreateTime(null);
        user.setCreateBy(SessionTool.getUserName());
        userService.save(user);
        return R.ok();
    }

    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        userService.removeByIds(ids);
        return R.ok();
    }

    @PostMapping(value = "edit")
    public R edit(User user) {
        userService.updateById(user);
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        userService.removeById(id);
        return R.ok();
    }

    @PostMapping(value = "reset/{id}")
    public R reset(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        user.setPassword("123456");
        userService.updateById(user);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(userService.getById(id));
    }

}

