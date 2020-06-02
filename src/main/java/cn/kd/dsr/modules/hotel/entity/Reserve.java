package cn.kd.dsr.modules.hotel.entity;

import cn.kd.dsr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 客房预订表
 * </p>
 * @since 2020-03-25 16:19:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("reserve_room")
public class Reserve extends BaseEntity {

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
     * 房间类型
     */
    private Integer roomType;

    /**
     * 房间号
     */
    private String roomNum;

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
     * 预订入住时间
     */
    private String startTime;

    /**
     * 预订退房时间
     */
    private String endTime;

    /**
     * 预付款
     */
    private BigDecimal pay;

    /**
     * 状态。0：未入住；2：已入住；3：退订；
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

}
