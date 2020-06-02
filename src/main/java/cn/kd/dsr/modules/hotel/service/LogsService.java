package cn.kd.dsr.modules.hotel.service;

import cn.kd.dsr.modules.hotel.entity.Logs;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 入住登记表 服务类
 * </p>
 * @since 2020-03-27 10:22:53
 */
public interface LogsService extends IService<Logs> {

    /**
     * 获取列表。分页
     * @param logs 查询参数
     * @return page
     */
    Page<Logs> listLogsPage(Logs logs);

}
