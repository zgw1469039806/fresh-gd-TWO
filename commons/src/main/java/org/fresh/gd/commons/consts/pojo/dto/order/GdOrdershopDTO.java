package org.fresh.gd.commons.consts.pojo.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @DATA 2019/4/24 14:03
 * @Author 郭家恒
 * @Description TODO
 */
@ApiModel("订单详细数据模型")
@Data
public class GdOrdershopDTO {
    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    private String orderid;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Integer comdityId;

    /**
     * 商品名
     */
    @ApiModelProperty("商品名")
    private String comdityName;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    private Integer num;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品图片URL")
    private String imageUrl;

    /**
     * 优惠方式
     */
    @ApiModelProperty("优惠方式")
    private Integer preferentialway;
}
