package cn.kd.dsr.modules.hotel.controller;

import cn.kd.dsr.common.dto.R;
import cn.kd.dsr.common.utils.HttpServletContextKit;
import cn.kd.dsr.modules.hotel.entity.User;
import cn.kd.dsr.modules.hotel.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录管理
 */
@RestController
@RequestMapping("hotel")
public class LoginContorller {

    @Autowired
    private Producer producer;
    @Autowired
    private UserService userService;

    @Value(value = "${kd.login.authcode.enable}")
    private boolean needAuthCode;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("login")
    public R loginCheck(@RequestParam("username") String username, @RequestParam("password") String password,
                        @RequestParam("vercode") String vercode,
                        HttpSession session){
//        System.out.println("从页面接收到的验证码为:"+vercode);
//        System.out.println("从session中取出的验证码为："+session.getAttribute("checkCode"));
        if(needAuthCode){
            String checkCode = session.getAttribute("checkCode").toString();
            if(!vercode.equalsIgnoreCase(checkCode)){
                return R.fail("验证码输入错误");
            }
        }
        User userFormDB = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), false);
        if(null == userFormDB || !userFormDB.getPassword().equals(password)){
            return R.fail("账号或密码错误");
        }

        session.setAttribute("user",userFormDB);
        return R.ok();
    }

    @GetMapping(value = "logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/hotel/login");
    }

    /**
     * 获取图片验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request) throws IOException {
        HttpServletResponse response = HttpServletContextKit.getHttpServletResponse();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);

        HttpSession session = request.getSession();
        session.setAttribute("checkCode",text);
//        System.out.println("设置验证码为：---------------------"+text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

}
