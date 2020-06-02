package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Guest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 宾客表 服务类
 * </p>
 * @since 2020-03-25 14:37:53
 */
public interface GuestService extends IService<Guest> {

    /**
     * 获取列表。分页
     * @param guest 查询参数
     * @return page
     */
    Page<Guest> listGuestPage(Guest guest);

    Integer savaGuest(Guest guest);

}
