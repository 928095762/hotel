package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 房间表 服务类
 * </p>
 * @since 2020-03-25 09:41:05
 */
public interface RoomService extends IService<Room> {

    /**
     * 获取列表。分页
     * @param room 查询参数
     * @return page
     */
    Page<Room> listRoomPage(Room room);

}
