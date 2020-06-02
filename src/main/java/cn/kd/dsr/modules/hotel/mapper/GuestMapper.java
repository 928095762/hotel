package cn.kd.dsr.modules.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.kd.dsr.modules.hotel.entity.Guest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * <p>
 * 宾客表 Mapper 接口
 * </p>
 * @since 2020-03-25 14:37:53
 */
public interface GuestMapper extends BaseMapper<Guest> {

    /**
     * 查询列表(分页)
     * @param guest 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Guest> selectGuestList(Guest guest, IPage page);


    Integer savaGuest(Guest guest);

}
