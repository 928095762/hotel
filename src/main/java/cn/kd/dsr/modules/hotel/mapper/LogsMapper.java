package cn.kd.dsr.modules.hotel.mapper;

import cn.kd.dsr.modules.hotel.entity.Logs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 入住登记表 Mapper 接口
 * </p>
 * @since 2020-03-27 10:22:53
 */
public interface LogsMapper extends BaseMapper<Logs> {

    /**
     * 查询列表(分页)
     * @param logs 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Logs> selectLogsList(Logs logs, IPage page);

}
