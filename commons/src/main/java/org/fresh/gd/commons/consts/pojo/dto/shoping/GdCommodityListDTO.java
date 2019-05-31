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
 * @data 2019/4/22 14:44
 */
@ApiModel("商品详细数据模型")
@Data
public class GdCommodityListDTO {

    /**
     * 商品名称
     */
    @ApiModelProperty("商品ID")
    private Integer comdityId;
    /**
     * 图片地址
     */
    @ApiModelProperty("商品图片Url")
    private String imagesurl;

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
     * 类型名称
     */
    @ApiModelProperty("类型名称")
    private String typename;

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

    /**
     * 折扣价格
     */
    @ApiModelProperty("折扣价格")
    private String discount;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String comdityBM;

    /**
     * 会员是否享有折扣
     */
    @ApiModelProperty("是否享有折扣")
    private Integer vipishige;

    /**
     * 图片等级
     */
    @ApiModelProperty("图片等级")
    private String imageslv;


    /**
     * 商品数
     */
    @ApiModelProperty("商品数")
    private Integer num;

    /**
     * 用户购物车商品编号
     */
    @ApiModelProperty("用户购物车编号")
    private Integer cartid;

    @ApiModelProperty("商品图片")
    GdImagesDTO gdImagesDTO;

}
