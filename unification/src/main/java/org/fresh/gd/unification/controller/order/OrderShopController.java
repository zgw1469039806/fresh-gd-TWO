package org.fresh.gd.unification.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.fresh.gd.unification.fegin.order.OrderShopFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/14 15:35
 * @Description:
 */
@RestController
@RequestMapping("/unification")
@Api(description = "订单详细接口")
public class OrderShopController {

    @Autowired
    OrderShopFeignService orderShopFeignService;

    @ApiOperation(value = "根据订单编号查询订单详细")
    @PostMapping("/selOrderShopById")
    public ResponseData<List<GdOrdershopDTO>> selOrderShopById(@RequestBody RequestData<String> orderId){
        return orderShopFeignService.selOrderShopById(orderId);
    }


}
