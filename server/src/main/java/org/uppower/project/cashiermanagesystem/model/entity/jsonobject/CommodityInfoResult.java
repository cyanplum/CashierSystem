package org.uppower.project.cashiermanagesystem.model.entity.jsonobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2520:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityInfoResult {
    @ApiModelProperty("商品条形码")
    private String barcode;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品价格 以分为单位")
    private Double price;
}
