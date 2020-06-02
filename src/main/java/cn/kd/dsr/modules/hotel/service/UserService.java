package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 * @since 2020-04-04 10:21:51
 */
public interface UserService extends IService<User> {

    /**
     * 获取列表。分页
     * @param user查询参数
     * @return page
     */
    Page<User> listUserlalalaPage(User user);

}
