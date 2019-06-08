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
public class GdDcMan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配送人员id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 配送人姓名
     */
    private String pname;

    /**
     * 登陆密码
     */
    private String loginpwd;

    /**
     * 工作手机号
     */
    private String phone;


}
