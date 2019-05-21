package org.gd.order.impl;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.apache.commons.lang.StringUtils;
import org.fresh.gd.commons.consts.api.order.GDOrderService;
import org.fresh.gd.commons.consts.api.shoping.GdCommodityService;
import org.fresh.gd.commons.consts.api.shoping.GdSupplierService;
import org.fresh.gd.commons.consts.consts.Consts;
import org.fresh.gd.commons.consts.exceptions.BizException;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.*;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdComdityparticularDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO;
import org.fresh.gd.commons.consts.utils.PageBean;
import org.fresh.gd.commons.consts.utils.VeDate;
import org.gd.order.entity.GdOrder;
import org.gd.order.entity.GdShoppingcart;
import org.gd.order.fegin.OrderFeginToGoods;
import org.gd.order.fegin.OrderFeginToShopping;
import org.gd.order.fegin.OrderFeginToVip;
import org.gd.order.mapper.GdOrderMapper;
import org.gd.order.mapper.GdOrdershopMapper;
import org.gd.order.mapper.GdShoppingcartMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @DATA 2019/4/24 13:56
 * @Author 郭家恒
 * @Description TODO
 */
@RestController
public class OrderServiceImpl implements GDOrderService {


    @Autowired
    private GdOrderMapper gdOrderMapper;

    @Autowired
    private GdOrdershopMapper gdOrdershopMapper;

    @Autowired
    private OrderFeginToShopping orderFeginToShopping;

    @Autowired
    private OrderFeginToGoods gdCommodityService;

    @Autowired
    private GdShoppingcartMapper gdShoppingcartMapper;

    @Autowired
    private OrderFeginToVip orderFeginToVip;

    /**
     * 功能描述:
     * 下订单。订单插入后减库存。
     *
     * @param gdOrderDTORequestData
     * @param: [gdOrderDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/4/24 13:53
     */
    @LcnTransaction
    @Transactional
    @Override
    public ResponseData<List> insertOrder(@RequestBody RequestData<GdOrderDTO> gdOrderDTORequestData) {
        //TODO: 判断是否使用会员余额支付 扣减会员余额 调用会员服务
        //TODO: 调用会员服务 增加积分 参数（店铺id、会员手机号、订单金额）
        RequestData<List<GdComdityparticularDTO>> requestData = new RequestData<>();
        ResponseData<List> responseData = new ResponseData<>();
        requestData.setData(gdOrderDTORequestData.getData().getTableData());
        if (gdOrderDTORequestData.getData().getOrderscene() == 0) {//如果交易场景为线上
            responseData = orderFeginToShopping.editStock(requestData);
            if (responseData.getMsg().equals("库存不足")) {
                return responseData;
            }
        }
        GdOrder gdOrder = new GdOrder();
        BeanUtils.copyProperties(gdOrderDTORequestData.getData(), gdOrder);

        //随机单号
        Random re = new Random();
        int i = re.nextInt(10000);
        gdOrder.setOrderid((new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())) + i);
        gdOrder.setOrderStat(4);
        gdOrder.setOrderTime(VeDate.getStringDate());
        gdOrderDTORequestData.getData().getAddressId();

        //插入订单
        int save = gdOrderMapper.insertOrder(gdOrder);
        ResponseData responseData1 = orderFeginToShopping.reduceStock(requestData);//减少库存
        if (responseData1.getCode() == 1000) {//如果减少成功
            for (GdComdityparticularDTO dto : gdOrderDTORequestData.getData().getTableData()) {
                //插入订单详细
                gdOrdershopMapper.insertOrderShop(gdOrder.getOrderid(), dto.getComdityId(), dto.getComdnum());
            }
        } else {
            throw new BizException("库存减扣失败");
        }

        //增加积分
        if (gdOrderDTORequestData.getData().getVipId() != null) {
            String str = gdOrderDTORequestData.getData().getOrdermoney().trim();
            Integer i1 = orderFeginToVip.upgVipIntegral(gdOrderDTORequestData.getData().getVipId().trim(), gdOrderDTORequestData.getData().getStoreid(), str);
        }

        responseData.setCode(Consts.Result.SUCCESS.getCode());
        return responseData;
    }

    /**
     * 功能描述
     * 根据用户id信息 查询购物车商品
     *
     * @param requestData
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO>>
     * @author zgw
     */
    @Override
    public ResponseData<List<ResponseData<GdCommodityListDTO>>> selGwcByShopId(@RequestBody RequestData<String>
                                                                                       requestData) {

        ResponseData<List<ResponseData<GdCommodityListDTO>>> responseData = new ResponseData<>();
        if (StringUtils.isEmpty(requestData.getData())) {
            throw new BizException("用于ID为空");
        }
        List<ResponseData<GdCommodityListDTO>> listDTOS = new ArrayList<>();

        List<GdShoppingcart> gdShoppingcart = gdShoppingcartMapper.queryCart(requestData.getData());

        for (GdShoppingcart gdShop : gdShoppingcart) {

            ResponseData<GdCommodityListDTO> commodityDTO = gdCommodityService.selOne(gdShop.getComdityId());
            commodityDTO.getData().setNum(gdShop.getNum());
            commodityDTO.getData().setCartid(gdShop.getCartid());
            listDTOS.add(commodityDTO);
        }
        responseData.setData(listDTOS);

        // TODO: 调用商品服务根据ID查询商品  返回商品结合
        return responseData;
    }

    /**
     * 功能描述:
     * 根据参数查询订单数量
     *
     * @param orderCountDTO
     * @param: [orderCountDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/13 16:06
     */
    @Override
    public Integer orderCount(OrderCountDTO orderCountDTO) {
        Integer i = gdOrderMapper.orderCount(orderCountDTO);
        return i;
    }

    /**
     * 功能描述:
     * 分页查询订单
     *
     * @param orderPageDTO
     * @param: [orderPageDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List                               <                               org.fresh.gd.commons.consts.pojo.dto.order.GdOrderDTO>>
     * @auther: Mr.Xia
     * @date: 2019/5/13 16:36
     */
    @Transactional
    @Override
    public ResponseData<PageBean<GdOrderDTO>> selOrderPage(@RequestBody RequestData<OrderPageDTO> orderPageDTO) {
        ResponseData<PageBean<GdOrderDTO>> responseData = new ResponseData<>();
        OrderPageDTO orderPageDTO1 = orderPageDTO.getData();

        if (orderPageDTO1.getPageNo() == 0) {
            responseData.setCode(Consts.Result.ERROR_PARAM.getCode());
            return responseData;
        }
        orderPageDTO1.setPageNo(orderPageDTO1.getPageNo() - 1);

        List<GdOrderDTO> orderPage = gdOrderMapper.selOrderPage(orderPageDTO1);

        //查询总数
        OrderCountDTO orderCountDTO = new OrderCountDTO();
        BeanUtils.copyProperties(orderPageDTO1, orderCountDTO);
        Integer ordercount = this.orderCount(orderCountDTO);

        //计算总页数
        Integer countPage = 0;
        if (ordercount % orderPageDTO1.getPageSize() == 0) {
            countPage = ordercount / orderPageDTO1.getPageSize();
        } else {
            countPage = ordercount / orderPageDTO1.getPageSize() + 1;
        }

        PageBean<GdOrderDTO> pageBean = new PageBean<>();
        pageBean.setTotalCount(ordercount);
        pageBean.setList(orderPage);
        pageBean.setTotalPage(countPage);
        pageBean.setCurrPage(orderPageDTO1.getPageNo());
        pageBean.setPageSize(orderPageDTO1.getPageSize());

        responseData.setData(pageBean);
        return responseData;
    }

    /**
     * 功能描述:
     * 根据订单编号修改订单状态 -
     *
     * @param: [orderId, ordStart]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/15 11:36
     */
    @Override
    public ResponseData<Integer> updOrderStartById(@RequestBody RequestData<OrderStartDTO> orderStartDTO) {
        ResponseData<Integer> responseData = new ResponseData<>();
        Integer i = gdOrderMapper.updOrderStartById(orderStartDTO.getData());
        if (i > 0) {
            return responseData;
        } else {
            responseData.setCode(Consts.Result.BIZ_ERROR.getCode());
            return responseData;
        }
    }



}
