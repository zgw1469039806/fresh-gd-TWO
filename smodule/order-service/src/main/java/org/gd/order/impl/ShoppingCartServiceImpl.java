package org.gd.order.impl;

import org.fresh.gd.commons.consts.api.order.GDShoppingCartService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.GdShoppingCartDTO;
import org.gd.order.entity.GdShoppingcart;
import org.gd.order.mapper.GdShoppingcartMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/6 10:24
 */
@RestController
public class ShoppingCartServiceImpl implements GDShoppingCartService {

    @Autowired
    private GdShoppingcartMapper gdShoppingcartMapper;


    /** 功能描述:
    *  查询
    * @param: [useraccount]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO>>
    * @auther: 贾轶飞
    * @date: 2019/5/8 17:11
    */
    @Override
    public ResponseData<List<GdOrdershopDTO>> queryCart(RequestData<String> useraccount) {


        return null;
    }

    /**
     * 功能描述:
     * 删除购物车中的商品
     *
     * @param: [cartid] 购物车中商品的编号
     * @return: org.fresh.gd.commons.consts.pojo.RequestData<java.lang.Integer>
     * @auther: 贾轶飞
     * @date: 2019/5/8 11:09
     */
    @Override
    public ResponseData<Integer> delCartGoods(@RequestBody RequestData<Integer> cartid) {
        ResponseData<Integer> responseData = new ResponseData<>();
        responseData.setData(gdShoppingcartMapper.delCartGoods(cartid.getData()));
        return responseData;
    }


    /**
     * 功能描述:
     * 添加购物车中的商品
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 贾轶飞
     * @date: 2019/5/8 11:54
     */
    @Override
    public ResponseData<Integer> addCartGoods(@RequestBody RequestData<GdShoppingCartDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        GdShoppingcart dto = new GdShoppingcart();
        BeanUtils.copyProperties(requestData.getData(), dto);
        dto.setUserid(requestData.getData().getUserid());
        if (requestData.getData().getComdityId() == null) {
            responseData.setCode(500);
            responseData.setMsg("接受的数据为空");
            return responseData;
        } else {
            //判断购物车中未结算的商品与要添加的商品是否重复PRIMARY
            Integer i=gdShoppingcartMapper.queryCount(requestData.getData().getUserid(), requestData.getData()
                    .getComdityId());
            if ( i== 1) {
                //重复修改其商品个数
                GdShoppingcart dto2=gdShoppingcartMapper.queryOne(requestData.getData().getUserid(), requestData
                        .getData()
                        .getComdityId());
                dto.setCartid(dto2.getCartid());
                dto.setNum(requestData.getData().getNum()+dto2.getNum());
                responseData.setData(gdShoppingcartMapper.updCartGoods(dto));
                responseData.setMsg("修改购物车中商品数目成功");
                return responseData;
            }else {
                //不重复将购物车中没有的商品加入购物车
                responseData.setData(gdShoppingcartMapper.addCartGoods(dto));
                responseData.setMsg("成功添加购物车");
                return responseData;
            }
        }

    }

    /** 功能描述:
    * 修改购物车中商品的个数
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/8 17:10
    */
    @Override
    public ResponseData<Integer> updCartGoods(@RequestBody RequestData<GdShoppingCartDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        GdShoppingcart dto = new GdShoppingcart();
        BeanUtils.copyProperties(requestData.getData(), dto);
        responseData.setData(gdShoppingcartMapper.updCartGoods(dto));

        return responseData;
    }

    /** 功能描述:
    * 购物车中条数
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/26 8:37
    */
    @Override
    public ResponseData<Integer> cartGoodsCount(@RequestBody RequestData<Integer> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        responseData.setData(gdShoppingcartMapper.cartGoodsCount(requestData.getData()));
        return responseData;
    }


}
