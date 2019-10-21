package org.uppower.project.cashiermanagesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("discounts")
@ApiModel(value="DiscountsEntity对象", description="优惠券")
public class DiscountsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "优惠方式")
    @TableField("pattern")
    private Integer pattern;

    @ApiModelProperty(value = "消费达到值")
    @TableField("target")
    private Integer target;

    @ApiModelProperty(value = "减免金额")
    @TableField("remission")
    private Integer remission;

    @ApiModelProperty(value = "打折数")
    @TableField("discount")
    private Integer discount;

    @ApiModelProperty(value = "权限")
    @TableField("auth")
    private Integer auth;

    @ApiModelProperty(value = "数量 -1 代表无限")
    @TableField("amount")
    private Integer amount;

    @ApiModelProperty("存储名，用于在minio中查找数据")
    @TableField("store_name")
    private String storeName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;


}
