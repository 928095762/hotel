package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Reserve;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 客房预订表 服务类
 * </p>
 * @since 2020-03-25 16:19:28
 */
public interface ReserveService extends IService<Reserve> {

    /**
     * 获取列表。分页
     * @param reserve 查询参数
     * @return page
     */
    Page<Reserve> listRoomPage(Reserve reserve);

}
