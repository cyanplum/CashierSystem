package org.uppower.project.cashiermanagesystem.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/18 21:49
 */

@Data
@ApiModel("奖池添加参数集")
public class LotteryVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "discount_id")
    private Integer discountId;

    @ApiModelProperty(value = "0加入奖池 1奖池外")
    private Integer status;

}
