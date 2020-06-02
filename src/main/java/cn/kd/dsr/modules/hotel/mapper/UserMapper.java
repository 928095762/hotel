package cn.kd.dsr.modules.hotel.mapper;

import cn.kd.dsr.modules.hotel.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 * @since 2020-04-04 10:21:51
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询列表(分页)
     * @param user 查询参数
     * @param page 分页参数
     * @return list
     */
    List<User> selectUserList(User user, IPage page);

}
