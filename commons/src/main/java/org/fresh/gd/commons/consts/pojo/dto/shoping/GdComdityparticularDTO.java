package org.fresh.gd.commons.consts.pojo.dto.shoping;

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



}
