package org.fresh.gd.commons.consts.pojo.dto.shoping;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/26 11:16
 */
@ApiModel("商品详细数据模型")
@Data
public class MerchandiseDetailsDTO {


    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Integer comdityId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String comdityname;

    /**
     * 商品类别
     */
    @ApiModelProperty("商品类别")
    private Integer comditytypeId;

    /**
     * 商品单位
     */
    @ApiModelProperty("商品单位")
    private String comditydw;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String comditydescribe;

    /**
     * 商品单价
     */
    @ApiModelProperty("商品单价")
    private String comdityprice;

    @ApiModelProperty("店铺名称")
    private String storename;

    /**
     * 图片编号
     */
    @ApiModelProperty("图片编号")
    private Integer imagesId;

    /**
     * 图片地址
     */
    @ApiModelProperty("商品图片Url")
    private String imagesurl;


    /**
     * 图片等级
     */
    @ApiModelProperty("图片等级")
    private String imageslv;

    /**
     * 商品详细图片
     */
    @ApiModelProperty("商品详细图片")
    private List<GdImages> imagesDTOS;
}

