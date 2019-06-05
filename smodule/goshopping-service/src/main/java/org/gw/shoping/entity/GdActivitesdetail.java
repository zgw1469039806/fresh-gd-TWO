package org.gw.shoping.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动详细表
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdActivitesdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @TableField("activites")
    private Integer activites;

    /**
     * 商品编号
     */
    @TableField("commodityId")
    private Integer commodityId;


    /**
     * 商品类别
     */
    @ApiModelProperty("商品类别")
    @TableField(exist = false)
    private Integer comditytypeId;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    @TableField(exist = false)
    private String comditydescribe;

    /**
     * 商品单价
     */
    @ApiModelProperty("商品单价")
    @TableField(exist = false)
    private String comdityprice;

    /**
     * 图片编号
     */
    @ApiModelProperty("图片编号")
    @TableField(exist = false)
    private Integer imagesId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    @TableField(exist = false)
    private String comdityname;

    @ApiModelProperty("门店id")
    @TableField(exist = false)
    private Integer storeid;

    @ApiModelProperty("商品图片Url")
    @TableField(exist = false)
    private String imagesurl;
}
