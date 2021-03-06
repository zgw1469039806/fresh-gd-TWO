package org.fresh.gd.commons.consts.pojo.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/13 15:20
 * @Description:
 */

@ApiModel("参数DTO-查询订单数量")
@Data
public class OrderCountDTO {

    @ApiModelProperty("订单编号")
    private String orderId;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("订单状态")
    private Integer orderStat;

    @ApiModelProperty("订单所属店铺")
    private Integer storeId;

    @ApiModelProperty("会员手机号")
    private String vipPhone;

    @ApiModelProperty("交易场景")
    private Integer orderScene;
}
