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
 * @date 2019/10/18 15:49
 */
@Data
public class DiscountsDto {

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

    @ApiModelProperty(value = "权限")
    private String auth;

    @ApiModelProperty(value = "数量 -1 代表无限")
    private Integer amount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
}
