package org.fresh.gd.commons.consts.pojo.dto.order;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/17 16:20
 */
@ApiModel("客户端订单商品数据模型")
@Data
public class GdWxOrderAndShopDTO {
    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    private String orderid;

    /**
     * 会员编号(0-不是会员 1=普通用户)
     */
    @ApiModelProperty("会员编号")
    private String vipId;

    /**
     * 交易场景(2-线上 1-线下)
     */
    @ApiModelProperty("交易场景")
    private Integer orderscene;

    /**
     * 交易手段(0-现金 1-支付宝 2-微信月 3-会员余额)
     */
    @ApiModelProperty("交易手段")
    private Integer ordermeans;

    /**
     * 交易类型(0-消费 1-退款)
     */
    @ApiModelProperty("交易类型")
    private Integer ordertype;

    /**
     * 交易金额
     */
    @ApiModelProperty("交易金额")
    private String ordermoney;

    /**
     * 交易时间
     */
    @ApiModelProperty("交易时间")
    private String orderTime;

    /**
     * 收款方(店铺ID)
     */
    @ApiModelProperty("店铺ID")
    private Integer storeid;

    /**
     * 订单状态  0:待付款   1:已付款/待发货   2:已取消  3:已发货/待确认  4:已完成  5:订单已取消
     */
    @ApiModelProperty("订单状态")
    private Integer orderStat;

    /**
     *  门店名称
     */
    @ApiModelProperty("门店名称")
    private String belongStoreNam;

    /**
     *   用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer userId;

    /**
     *  抹零
     */
    @ApiModelProperty("抹零")
    private String priceml;

    /**
     *   送货地址
     */
    @ApiModelProperty("送货地址")
    private String address;

    /**
     *   收货人手机号
     */
    @ApiModelProperty("收货人手机号")
    private String phone;

    /**
     *   订单详细商品集合
     */
    @ApiModelProperty("订单详细商品集合")
   private List<GdOrdershopDTO> list;
}
