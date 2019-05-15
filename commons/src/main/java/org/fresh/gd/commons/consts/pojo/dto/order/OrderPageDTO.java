package org.fresh.gd.commons.consts.pojo.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/13 15:01
 * @Description: 查询订单分页
 */
@ApiModel("参数DTO-分页查询订单")
@Data
public class OrderPageDTO {

    @ApiModelProperty("订单编号")
    private String orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("开始时间")
    private String startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @ApiModelProperty("从第几页开始")
    private Integer pageNo;

    @ApiModelProperty("每页显示条数")
    private Integer pageSize;


}
