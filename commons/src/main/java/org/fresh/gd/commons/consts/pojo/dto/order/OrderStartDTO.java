package org.fresh.gd.commons.consts.pojo.dto.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/15 11:56
 * @Description:
 */
@Data
@Api("传参DTO-修改订单状态")
public class OrderStartDTO {

    @NotEmpty
    @ApiModelProperty("订单编号")
    private String orderId;

    @NotEmpty
    @ApiModelProperty("订单状态")
    private Integer ordStart;
}
