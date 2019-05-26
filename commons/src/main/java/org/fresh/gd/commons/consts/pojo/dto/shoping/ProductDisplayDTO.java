package org.fresh.gd.commons.consts.pojo.dto.shoping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 贾轶飞
 * @dat e2019/5/26 11:02
 */
@ApiModel("展示商品数据模型")
@Data
public class ProductDisplayDTO {
    /**
     * 供应商编号
     */
    @ApiModelProperty("活动id")
    private Integer activityId;
    /**
     * 供应商编号
     */
    @ApiModelProperty("商品id")
    private Integer comdityId;
    /**
     * 供应商编号
     */
    @ApiModelProperty("商品名")
    private String comdityname;
    /**
     * 供应商编号
     */
    @ApiModelProperty("折扣价")
    private String discount;
    /**
     * 供应商编号
     */
    @ApiModelProperty("供应商编号")
    private String storeid;
    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private String imageurl;
}
