package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Logs;
import cn.kd.dsr.modules.hotel.mapper.LogsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 入住登记表 服务实现类
 * </p>
 * @since 2020-03-27 10:22:53
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements LogsService {

    @Override
    public Page<Logs> listLogsPage(Logs logs) {
        Page<Logs> page = new Page<>(logs.getCurrent(), logs.getSize());
        List<Logs> logss = baseMapper.selectLogsList(logs, page);
        return page.setRecords(logss);
    }

}
