package org.gw.shoping.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品进货表
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdReplenish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 进货ID
     */
    @TableId("replenishId")
    private Integer replenishId;

    /**
     * 进货时间
     */
    @TableField("replenishTime")
    private String replenishTime;

    /**
     * 采购人员id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 店铺ID
     */
    private Integer storeid;


}
