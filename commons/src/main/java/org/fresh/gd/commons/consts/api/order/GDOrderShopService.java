package org.fresh.gd.commons.consts.api.order;

import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/14 15:22
 * @Description:
 */
@RequestMapping("/GDOrderShopService")
public interface GDOrderShopService {


    /**
    *
    * 功能描述:
    *   根据订单编号查询订单详细
    * @param: [orderId]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO>>
    * @auther: Mr.Xia
    * @date: 2019/5/14 15:24
    */
    @PostMapping("/selOrderShopById")
    ResponseData<Map<String,Object>> selOrderShopById(RequestData<String> orderId);

    /**
     *
     * 功能描述:
     *   根据订单编号查询订单详细
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO>>
     * @auther: Mr.Xia
     * @date: 2019/5/14 15:24
     */
    @PostMapping("/selOrderShopByIdTWO")
    ResponseData<Map<String,Object>> selOrderShopByIdTWO(RequestData<String> orderId);
}
