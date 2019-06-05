package org.fresh.gd.commons.consts.pojo.dto.shoping;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class GdActivitesdetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    private Integer activites;

    /**
     * 商品编号
     */
    @TableField("commodityId")
    private Integer commodityId;

    /**
     * 商品名称
     */
    private String comdityname;

    /**
     * 折扣价
     */
    private String discount;

    /**
     * 商品单价
     */
    private String comdityprice;

}
