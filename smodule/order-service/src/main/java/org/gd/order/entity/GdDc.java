package org.gd.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdDc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配送详情id
     */
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    /**
     * 订单id
     */
    private String orderid;

    /**
     * 配送人员id
     */
    private Integer pid;

    /**
     * 1.差 2.一般 3.良好 4.优秀
     */
    private Integer score;


}
