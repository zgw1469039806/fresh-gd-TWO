package org.fresh.gd.commons.consts.pojo.dto.shoping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @DATA 2019/5/14 23:20
 * @Author 郭家恒
 * @Description TODO
 */
@ApiModel("店铺间商品数据同步DTO")
@Data
public class synchronizationDTO {
    /**
     * 目标店铺ID
     */
    @ApiModelProperty("目标店铺ID")
    private Integer storeid;

    /**
     * 商品集合
     */
    @ApiModelProperty("商品详细集合")
    private List<GdComdityparticularDTO> list;
}
