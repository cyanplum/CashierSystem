package org.uppower.project.cashiermanagesystem.model.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/1915:41
 */
@ApiModel("规则返回集")
@Data
public class RulesResult {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "每日登陆奖励")
    private Integer loginAward;

    @ApiModelProperty(value = "青铜所需经验")
    private Integer bronze;

    @ApiModelProperty(value = "白银所需经验")
    private Integer silver;

    @ApiModelProperty(value = "黄金所需经验")
    private Integer gold;

    @ApiModelProperty(value = "铂金所需经验")
    private Integer platinum;

    @ApiModelProperty(value = "钻石所需经验")
    private Integer diamond;

    @ApiModelProperty(value = "消费返积分")
    private Integer reIntegral;

    @ApiModelProperty(value = "消费返经验")
    private Integer reExperience;
}
