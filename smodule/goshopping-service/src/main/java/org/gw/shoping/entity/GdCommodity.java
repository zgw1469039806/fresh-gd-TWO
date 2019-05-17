package org.gw.shoping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdCommodity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "comdityId", type = IdType.AUTO)
    private Integer comdityId;

    /**
     * 商品名称
     */
    @TableField("comdityname")
    private String comdityname;

    /**
     * 商品类别
     */
    @TableField("comditytypeId")
    private Integer comditytypeId;

    /**
     * 商品单位
     */
    @TableField("comditydw")
    private String comditydw;

    /**
     * 商品描述
     */
    @TableField("comditydescribe")
    private String comditydescribe;

    /**
     * 商品单价
     */
    @TableField("comdityprice")
    private String comdityprice;

}
