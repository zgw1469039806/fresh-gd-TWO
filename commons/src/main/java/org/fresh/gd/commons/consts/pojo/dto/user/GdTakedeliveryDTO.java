package org.fresh.gd.commons.consts.pojo.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/16 16:28
 * @Description:
 */

@Data
@ApiModel("地址信息DTO")
public class GdTakedeliveryDTO {

    /**
     * 地址编号
     */
    private Integer takedeliveryidid;

    /**
     * 用户编号
     */
    private Integer userid;

    /**
     * 地址
     */
    private String address;

    /**
     * 更新人
     *//*
    @TableField("UPDATED_BY")
    private String updatedBy;

    *//**
     * 更新时间
     *//*
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;*/

    /**
     * 手机号
     */
    private String phone;
    /**
     * 收获人姓名
     */
    private String consignee;
    /**
     * 默认状态
     */
    private Integer status;
}
