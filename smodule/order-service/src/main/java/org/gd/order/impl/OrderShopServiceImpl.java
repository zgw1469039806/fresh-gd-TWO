package org.gd.order.impl;

import io.swagger.annotations.ApiOperation;
import org.fresh.gd.commons.consts.api.order.GDOrderService;
import org.fresh.gd.commons.consts.api.order.GDOrderShopService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.gd.order.fegin.OrderFeginToGoods;
import org.gd.order.mapper.GdOrdershopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/14 15:25
 * @Description:
 */
@RestController
public class OrderShopServiceImpl implements GDOrderShopService {

    @Autowired
    GdOrdershopMapper gdOrdershopMapper;

    @Autowired
    OrderFeginToGoods orderFeginToGoods;

    /**
     * 功能描述:
     * 根据订单编号查询订单详细
     *
     * @param orderId
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List < org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO>>
     * @auther: Mr.Xia
     * @date: 2019/5/14 15:24
     */
    @Override
    public ResponseData<List<GdOrdershopDTO>> selOrderShopById(@RequestBody RequestData<String> orderId) {
        ResponseData<List<GdOrdershopDTO>> responseData = new ResponseData<>();

        Map<String,Object> map = new ConcurrentHashMap<>();

        List<GdOrdershopDTO> list = gdOrdershopMapper.selOrderShopById(orderId.getData());
        List<Integer> integerList = new ArrayList<>();
        for (GdOrdershopDTO gd : list) {
            integerList.add(gd.getComdityId());
        }

        //TODO:调用商品






        responseData.setData(list);
        return responseData;
    }
}
