package org.uppower.project.cashiermanagesystem.model.dto;

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
 * @date 2019/10/2121:39
 */
@Data
public class UserDiscountDto {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "优惠方式Enum")
    private Integer pattern;

    @ApiModelProperty(value = "优惠方式name")
    private String name;

    @ApiModelProperty(value = "消费达到值")
    private Integer target;

    @ApiModelProperty(value = "减免金额")
    private Integer remission;

    @ApiModelProperty(value = "打折数")
    private Integer discount;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "0未使用 1已使用 2 未到时间 3 已过期")
    private Integer status;
}
