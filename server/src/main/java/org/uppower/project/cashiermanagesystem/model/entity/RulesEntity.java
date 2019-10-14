package org.uppower.project.cashiermanagesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规则表
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rules")
@ApiModel(value="RulesEntity对象", description="规则表")
public class RulesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "每日登陆奖励")
    @TableField("login_award")
    private Integer loginAward;

    @ApiModelProperty(value = "青铜所需经验")
    @TableField("bronze")
    private Integer bronze;

    @ApiModelProperty(value = "白银所需经验")
    @TableField("silver")
    private Integer silver;

    @ApiModelProperty(value = "黄金所需经验")
    @TableField("gold")
    private Integer gold;

    @ApiModelProperty(value = "铂金所需经验")
    @TableField("platinum")
    private Integer platinum;

    @ApiModelProperty(value = "钻石所需经验")
    @TableField("diamond")
    private Integer diamond;

    @ApiModelProperty(value = "消费返积分")
    @TableField("re_integral")
    private Integer reIntegral;

    @ApiModelProperty(value = "消费返经验")
    @TableField("re_experience")
    private Integer reExperience;


}
