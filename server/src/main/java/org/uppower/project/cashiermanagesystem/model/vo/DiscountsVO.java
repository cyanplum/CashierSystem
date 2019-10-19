package org.uppower.project.cashiermanagesystem.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @date 2019/10/18 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountsVO {

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
    private Double discount;

    @ApiModelProperty(value = "权限")
    private String auth;

    @ApiModelProperty(value = "数量 -1 代表无限")
    private Integer amount;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
}
