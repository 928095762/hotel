package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.User;
import cn.kd.dsr.modules.hotel.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 * @since 2020-04-04 10:21:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> listUserlalalaPage(User user) {
        Page<User> page = new Page<>(user.getCurrent(), user.getSize());
        List<User> userlalalas = baseMapper.selectUserList(user, page);
        return page.setRecords(userlalalas);
    }

}
