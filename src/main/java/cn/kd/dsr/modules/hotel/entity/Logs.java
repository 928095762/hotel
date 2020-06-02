package cn.kd.dsr.modules.hotel.entity;

import cn.hutool.core.date.DateTime;
import cn.kd.dsr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 入住登记表
 * </p>
 * @since 2020-03-27 10:22:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("room_logs")
public class Logs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房间id
     */
    private Integer roomId;

    /**
     * 房间号
     */
    private String roomNum;

    /**
     * 房间类型
     */
    private Integer roomType;

    /**
     * 宾客id
     */
    private Integer guestId;

    /**
     * 宾客姓名
     */
    private String guestName;

    /**
     * 宾客电话
     */
    private Long phonenum;

    /**
     * 入住时间
     */
    private String startTime;

    /**
     * 退房时间
     */
    private String endTime;

    /**
     * 状态。1：住房中；2：已退房；
     */
    private Integer status;

    /**
     * 预付款
     */
    private BigDecimal pay;

    /**
     * 应付款
     */
    private BigDecimal price;

    /**
     * 付款状态。0：未付款；1：已结清；
     */
    private Integer priceStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

}
