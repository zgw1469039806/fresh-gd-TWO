package org.fresh.gd.commons.consts.api.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.*;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO;
import org.fresh.gd.commons.consts.utils.PageBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @DATA 2019/4/24 13:44
 * @Author 郭家恒
 * @Description TODO
 */
@RequestMapping("/OrderService")
public interface GDOrderService {

    /**
     * 功能描述:
     *
     * @param: [gdOrderDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/4/24 13:53
     */
    @PostMapping("/insertOrder")
    ResponseData<List> insertOrder(RequestData<GdOrderDTO> gdOrderDTORequestData);


    /**
     * 功能描述:
     * 根据用户id信息 查询购物车商品
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List   <   org.fresh.gd.commons.consts.pojo.ResponseData   <   org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO>>>
     * @auther: 贾轶飞
     * @date: 2019/5/7 13:55
     */
    @PostMapping("/selGwcByShopId")
    ResponseData<List<ResponseData<GdCommodityListDTO>>> selGwcByShopId(RequestData<String> requestData);

    /**
     * 功能描述:
     * 根据参数查询订单数量
     *
     * @param: [orderCountDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/13 16:06
     */
    @PostMapping("/orderCount")
    Integer orderCount(OrderCountDTO orderCountDTO);


    /**
     * 功能描述:
     * 分页查询订单
     *
     * @param: [orderPageDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List   <   org.fresh.gd.commons.consts.pojo.dto.order.GdOrderDTO>>
     * @auther: Mr.Xia
     * @date: 2019/5/13 16:36
     */
    @PostMapping("/selOrderPage")
    ResponseData<PageBean<GdOrderDTO>> selOrderPage(RequestData<OrderPageDTO> orderPageDTO);

    /**
     * 功能描述:
     * 根据订单编号修改订单状态
     *
     * @param: [orderId, ordStart]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/15 11:36
     */
    @PostMapping("/updOrderStartById")
    ResponseData<Integer> updOrderStartById(RequestData<OrderStartDTO> orderStartDTO);


    /** 功能描述:
    *用户订单
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.order.GdUserOrderDTO>
    * @auther: 贾轶飞
    * @date: 2019/5/23 10:16
    */
    @PostMapping("/userOrderQuery")
    ResponseData<List<GdUserOrderDTO>> userOrderQuery(RequestData<GdUserOrderDTO> requestData);

    /**
     * 查询统计报表
     * @Date: 11:47 2019/5/24
     * @Author: 郭家恒
     */
    @PostMapping("/QueryDeportForm")
    ResponseData<List<Float>> QueryDeportForm(GdDeportFormDTO gdDeportFormDTO);

    /**
    *
    * 功能描述:
    *   线上-根据订单编号修改订单状态为已支付（用于小程序订单支付）
    * @param: [orderStartDTO]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/24 10:03
    */
    @PostMapping("/updOrderStartPay")
    ResponseData<Integer> updOrderStartPay(@RequestParam("orderId") String orderId);


    /**
    *
    * 功能描述:
    *   线上-根据订单编号修改订单状态为确认收货（用于小程序订单确认收货）
    * @param: [orderId]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/24 10:18
    */
    @PostMapping("/updOrderStartOK")
    ResponseData<Integer> updOrderStartOK(@RequestParam("orderId") String orderId);

    /**
    *
    * 功能描述:
    *   线上-根据订单编号修改订单状态为申请退款（用于小程序点击申请退款）
    * @param: [orderId]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/24 11:17
    */
    @PostMapping("/updOrderStartTuiPay")
    ResponseData<Integer> updOrderStartTuiPay(@RequestParam("orderId") String orderId);


    /**
    *
    * 功能描述:
    *   线上-根据订单编号修改订单状态为到店支付（用于小程序点击到店支付）
    * @param: [orderId]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/24 11:20
    */
    @PostMapping("/updOrderStartToGoodsPay")
    ResponseData<Integer> updOrderStartToGoodsPay(@RequestParam("orderId") String orderId);

    /**
     *
     * 功能描述:
     *   线上条数
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: jiayifei
     * @date: 2019/5/25
     */
    @PostMapping("/queryCountOrder")
    ResponseData<Map<String,Integer>> queryCountOrder(RequestData<Integer> requestData);

    /** 功能描述:
    * 支付完成的完整的订单
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/25 18:28
    */
    @PostMapping("/payOrder")
    ResponseData<Integer> payOrder(RequestData<GdUserOrderDTO> requestData);

    /** 功能描述:
    * 取消订单
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/25 18:30
    */
    @PostMapping("/removeOrder")
    ResponseData<Integer> removeOrder(RequestData<String> requestData);
}
