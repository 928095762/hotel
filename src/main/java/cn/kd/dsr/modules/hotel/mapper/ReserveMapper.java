package cn.kd.dsr.modules.hotel.mapper;

import cn.kd.dsr.modules.hotel.entity.Reserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 客房预订表 Mapper 接口
 * </p>
 * @since 2020-03-25 16:19:28
 */
public interface ReserveMapper extends BaseMapper<Reserve> {

    /**
     * 查询列表(分页)
     * @param reserve 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Reserve> selectRoomList(Reserve reserve, IPage page);

}
