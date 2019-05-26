package org.gd.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 统计报表
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdReportform implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计报表id
     */
    @TableId(value = "reid", type = IdType.AUTO)
    private Integer reid;

    /**
     * 外键，订单id
     */
    private String orderid;

    /**
     * 门店id
     */
    private Integer storeid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     *  成本
     */
    private float rfmoney;
}
