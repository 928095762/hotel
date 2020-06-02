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
 * 房间表
 * </p>
 * @since 2020-03-25 09:41:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hotel_room")
public class Room extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房间号
     */
    private String roomNum;

    /**
     * 房间类型。1：单人间；2：标准双人间；3：三人间；4：大床房；
     */
    private Integer type;

    /**
     * 状态。0：禁用；1：空闲；2：有预定；
     */
    private Integer status;

    /**
     * 价格
     */
    private BigDecimal price;

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
