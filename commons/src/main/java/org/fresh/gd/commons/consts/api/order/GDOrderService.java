package org.fresh.gd.commons.consts.api.order;

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



}
