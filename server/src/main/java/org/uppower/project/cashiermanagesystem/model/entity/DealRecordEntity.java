package org.uppower.project.cashiermanagesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.Commodityinfo;

/**
 * <p>
 * 交易记录表
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("deal_record")
@ApiModel(value="DealRecordEntity对象", description="交易记录表")
public class DealRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "userid")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "交易时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "交易商品")
    @TableField(el = "commodity,typeHandler=org.uppower.project.cashiermanagesystem.dao.typehandler.ListTypeHandler")
    private List<Commodityinfo> commodity;

    @ApiModelProperty(value = "总价")
    @TableField("total_prices")
    private Integer totalPrices;


}
