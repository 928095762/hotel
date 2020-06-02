package cn.kd.dsr.common.config;


import cn.kd.dsr.modules.hotel.controller.LoginContorller;
import cn.kd.dsr.modules.hotel.controller.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/hotel/reserve/outAddPage", //用户网上预定页面请求接口
                "/hotel/reserve/outAdd", //用户网上预定请求接口
                "/hotel/login",          //登录
                "/hotel/captcha.jpg",    //验证码
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.png",
                "/**/*.ttf"
        );
    }

}

