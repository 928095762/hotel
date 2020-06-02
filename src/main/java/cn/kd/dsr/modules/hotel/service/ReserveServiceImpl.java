package cn.kd.dsr.modules.hotel.service;


import cn.kd.dsr.modules.hotel.entity.Reserve;
import cn.kd.dsr.modules.hotel.mapper.ReserveMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客房预订表 服务实现类
 * </p>
 * @since 2020-03-25 16:19:28
 */
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {

    @Override
    public Page<Reserve> listRoomPage(Reserve Reserve) {
        Page<Reserve> page = new Page<>(Reserve.getCurrent(), Reserve.getSize());
        List<Reserve> rooms = baseMapper.selectRoomList(Reserve, page);
        return page.setRecords(rooms);
    }

}
