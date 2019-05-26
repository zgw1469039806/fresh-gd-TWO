package org.gd.order.impl;

import io.swagger.annotations.ApiOperation;
import org.fresh.gd.commons.consts.api.order.GDOrderService;
import org.fresh.gd.commons.consts.api.order.GDOrderShopService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.GdUserOrderDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdImagesDTO;
import org.fresh.gd.commons.consts.pojo.dto.user.GdTakedeliveryDTO;
import org.fresh.gd.commons.consts.pojo.dto.user.UserAddressDTO;
import org.gd.order.fegin.OrderFeginToGoods;
import org.gd.order.fegin.OrderFeginToUser;
import org.gd.order.mapper.GdOrderMapper;
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

    @Autowired
    OrderFeginToUser orderFeginToUser;

    @Autowired
    GdOrderMapper gdOrderMapper;

    /**
     * 功能描述:
     * 根据订单编号查询订单详细
     *
     * @param orderId
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List   <   org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO>>
     * @auther: Mr.Xia
     * @date: 2019/5/14 15:24
     */
    @Override
    public ResponseData<Map<String, Object>> selOrderShopById(@RequestBody RequestData<String> orderId) {
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();

        Map<String, Object> map = new ConcurrentHashMap<>();

        List<GdOrdershopDTO> ordList = gdOrdershopMapper.selOrderShopById(orderId.getData());
        List<Integer> integerList = new ArrayList<>();
        for (GdOrdershopDTO gd : ordList) {
            integerList.add(gd.getComdityId());
        }
        //TODO:调用商品
        ResponseData<List<GdCommodityDTO>> comList = orderFeginToGoods.QueryShopByIds(integerList);
        map.put("ordList", ordList);
        map.put("comList", comList.getData());
        responseData.setData(map);
        return responseData;
    }

    @Override
    public ResponseData<Map<String, Object>> selOrderShopByIdTWO(@RequestBody RequestData<String> orderId) {
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        GdUserOrderDTO gdUserOrderDTO = new GdUserOrderDTO();
        gdUserOrderDTO.setOrderid(orderId.getData());
        Map<String, Object> map = new ConcurrentHashMap<>();
        List<GdUserOrderDTO> ls=gdOrderMapper.userOrderQuery(gdUserOrderDTO);
        RequestData<UserAddressDTO> requestData=new RequestData<>();
        for (GdUserOrderDTO ds:ls
             ) {
            UserAddressDTO uad=new UserAddressDTO();
            uad.setStatus(1);
            uad.setUserid(ds.getUserId());
            requestData.setData(uad);

           //获取用户的默认地址
        }
        ResponseData<GdTakedeliveryDTO> ads= orderFeginToUser.defAddress(requestData);
        List<GdOrdershopDTO> ordList = gdOrdershopMapper.selOrderShopById(orderId.getData());
        List<Integer> integerList = new ArrayList<>();
        for (GdOrdershopDTO gd : ordList) {
            integerList.add(gd.getComdityId());
        }
        //TODO:调用商品
        ResponseData<List<GdCommodityDTO>> comList = orderFeginToGoods.QueryShopByIdsTwo(integerList);
        List<GdCommodityDTO> list = orderFeginToGoods.QueryShopByIdsTwo(integerList).getData();
        for (GdCommodityDTO dto : list
                ) {
            ResponseData<GdCommodityListDTO> gcl = orderFeginToGoods.selOne(dto.getComdityId());
            GdImagesDTO imagesDTO = new GdImagesDTO();
            imagesDTO.setImagesurl(gcl.getData().getImagesurl());
            dto.setGdImagesDTO(imagesDTO);
        }
        comList.setData(list);
        map.put("ordx", ls);
        map.put("ads",ads.getData());
        map.put("ordList", ordList);
        map.put("comList", comList.getData());
        responseData.setData(map);
        return responseData;
    }

}
