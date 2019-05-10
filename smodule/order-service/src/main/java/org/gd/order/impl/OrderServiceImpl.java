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
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrderDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdComdityparticularDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO;
import org.gd.order.entity.GdOrder;
import org.gd.order.entity.GdShoppingcart;
import org.gd.order.fegin.OrderFeginToGoods;
import org.gd.order.fegin.OrderFeginToShopping;
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

        int save = gdOrderMapper.insertOrder(gdOrder);//插入订单
        ResponseData responseData1 = orderFeginToShopping.reduceStock(requestData);//减少库存
        if (responseData1.getCode() == 1000) {
            for (GdComdityparticularDTO dto : gdOrderDTORequestData.getData().getTableData()) {
                gdOrdershopMapper.insertOrderShop(gdOrder.getOrderid(), dto.getComdityId(), dto.getComdnum());//插入订单详细
            }
        }
        responseData.setCode(Consts.Result.SUCCESS.getCode());
        return responseData;
    }

    /**
     * 功能描述
     * 根据用户id信息 查询购物车商品
     * @param requestData
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List < org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO>>
     * @author zgw
     */
    @Override
    public ResponseData<List<ResponseData<GdCommodityListDTO>>> selGwcByShopId(@RequestBody String
                                                                                       requestData) {

        ResponseData<List<ResponseData<GdCommodityListDTO>>> responseData = new ResponseData<>();
        if (StringUtils.isEmpty(requestData)) {
            throw new BizException("用于ID为空");
        }
        List<ResponseData<GdCommodityListDTO>> listDTOS = new ArrayList<>();

        List<GdShoppingcart> gdShoppingcart = gdShoppingcartMapper.queryCart(requestData);

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
}
