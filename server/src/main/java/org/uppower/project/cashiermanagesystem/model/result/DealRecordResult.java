package org.uppower.project.cashiermanagesystem.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfo;
import org.uppower.project.cashiermanagesystem.model.entity.jsonobject.CommodityInfoResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2416:07
 */
@ApiModel("交易记录具体信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealRecordResult {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "userid")
    private Integer userId;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "订单号")
    private String orderNumber;

    @ApiModelProperty(value = "交易商品")
    private List<CommodityInfoResult> commodity;

    @ApiModelProperty(value = "总价")
    private Double totalPrices;
}
