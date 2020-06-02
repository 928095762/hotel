package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.kd.dsr.modules.hotel.mapper.RoomMapper;

import java.util.List;

/**
 * <p>
 * 房间表 服务实现类
 * </p>
 * @since 2020-03-25 09:41:05
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public Page<Room> listRoomPage(Room room) {
        Page<Room> page = new Page<>(room.getCurrent(), room.getSize());
        List<Room> rooms = baseMapper.selectRoomList(room, page);
        return page.setRecords(rooms);
    }

}
