package org.fresh.gd.commons.consts.pojo.dto.shoping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @DATA 2019-04-12 10:28
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@ApiModel("商品详情数据模型")
@Data
public class GdComdityparticularDTO {

    @ApiModelProperty("商品ID")
    private Integer comdityId;

    @ApiModelProperty("是否打折")
    private Integer isnodiscount;

    @ApiModelProperty("库存数量、商品数量")
    private Integer comdnum;

    @ApiModelProperty("对应积分")
    private String corresponding;

    @ApiModelProperty("所属店铺ID")
    private Integer storeid;

    @ApiModelProperty("折扣价")
    private String discount;

    @ApiModelProperty("进货价")
    private String puprice;

    @ApiModelProperty("商品状态 0-上架中 1-下架")
    private Integer comstate;

    @ApiModelProperty("会员是否可享折扣")
    private Integer vipishige;

    @ApiModelProperty("商品编码")
    private String comdityBM;
}
