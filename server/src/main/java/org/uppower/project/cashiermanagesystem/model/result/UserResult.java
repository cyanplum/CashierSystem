package org.uppower.project.cashiermanagesystem.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
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
 * @date 2019/10/2114:53
 */
@ApiModel("用户信息返回集")
@Data
public class UserResult {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "电话号")
    private String phone;

    @ApiModelProperty(value = "vip码")
    private String vipcode;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "积分")
    private Integer Integral;

    @ApiModelProperty(value = "经验")
    private Integer experience;

    @ApiModelProperty(value = "等级")
    private String Grade;

}
