package org.gd.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId
    private String orderid;

    /**
     * 会员编号(0-不是会员 1=普通用户)
     */
    @TableField("vipId")
    private String vipId;

    /**
     * 交易场景(2-线上 1-线下)
     */
    @TableField("orderscene")
    private Integer orderscene;

    /**
     * 交易手段(0-现金 1-支付宝 2-微信月 3-会员余额)
     */
    @TableId("ordermeans")
    private Integer ordermeans;

    /**
     * 交易类型(0-消费 1-退款)
     */
    @TableId("ordertype")
    private Integer ordertype;

    /**
     * 交易金额
     */
    @TableId("ordermoney")
    private String ordermoney;

    /**
     * 交易时间
     */
    @TableField("orderTime")
    private String orderTime;

    /**
     * 收款方(店铺ID)
     */
    @TableField("storeid")
    private Integer storeid;

    /**
     * 订单状态  0:待付款   1:已付款/待发货   2:已取消  3:已发货/待确认  4:已完成  5:订单已取消
     */
    @TableField("orderStat")
    private Integer orderStat;

    /**
     * 门店名称
     */
    @TableField("belongStoreNam")
    private String belongStoreNam;

    /**
     * 用户ID
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 抹零
     */
    @TableField("priceml")
    private String priceml;

    /**
     * 收获地址ID
     */
    private Integer addressId;


    /**
     * 送货地址
     */
    @TableField("address")
    private String address;

    /**
     * 收货人手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 收货人
     */
    @ApiModelProperty("收货人")
    private String recipients;


    @ApiModelProperty("应付价格")
    private String comdityprice;

    /**
     * 实付价格
     */
    @ApiModelProperty("实付价格")
    private String comditytrueprice;

    /**
     * 找零
     */
    @ApiModelProperty("找零")
    private String gchange;
}
