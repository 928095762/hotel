package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Guest;
import cn.kd.dsr.modules.hotel.mapper.GuestMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 宾客表 服务实现类
 * </p>
 * @since 2020-03-25 14:37:53
 */
@Service
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements GuestService {

    @Override
    public Page<Guest> listGuestPage(Guest guest) {
        Page<Guest> page = new Page<>(guest.getCurrent(), guest.getSize());
        List<Guest> guests = baseMapper.selectGuestList(guest, page);
        return page.setRecords(guests);
    }

    @Override
    public Integer savaGuest(Guest guest){
        baseMapper.savaGuest(guest);
        return guest.getId();
    }


}
