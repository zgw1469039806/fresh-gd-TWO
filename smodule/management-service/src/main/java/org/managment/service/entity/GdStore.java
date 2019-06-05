package org.managment.service.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdStore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺ID
     */
    @TableField("storeid")
    private Integer storeid;

    /**
     * 店铺名称
     */
    @TableField("storename")
    private String storename;

    /**
     * 店铺地址
     */
    @TableField("storeaddress")
    private String storeaddress;

    /**
     * 店铺LogoUrl
     */
    @TableField("storeaLogo")
    private String storeaLogo;

    /**
     * 店铺的经纬度
     */
    @TableField("lal")
    private String lal;

}
