package org.uppower.project.cashiermanagesystem.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 条形码表
 * </p>
 *
 * @author tuqikang
 * @since 2019-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("barcode")
@ApiModel(value="BarcodeEntity对象", description="条形码表")
public class BarcodeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "条形码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private Integer price;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除 0未删除 1已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("存储名，用于在minio中查找数据")
    @TableField("store_name")
    private String storeName;


}
