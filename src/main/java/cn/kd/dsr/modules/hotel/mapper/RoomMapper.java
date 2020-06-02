package cn.kd.dsr.modules.hotel.mapper;

import cn.kd.dsr.modules.hotel.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 房间表 Mapper 接口
 * </p>
 * @since 2020-03-25 09:41:05
 */
public interface RoomMapper extends BaseMapper<Room> {

    /**
     * 查询列表(分页)
     * @param room 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Room> selectRoomList(Room room, IPage page);

}
