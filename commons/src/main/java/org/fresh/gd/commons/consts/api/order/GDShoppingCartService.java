package org.fresh.gd.commons.consts.api.order;

import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.GdShoppingCartDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/6 10:13
 */
@RequestMapping("/ShoppingCartService")
public interface GDShoppingCartService {


    /** 功能描述:
    *  根据用户查询当前用户购物车信息
    * @param: [useraccount] 用户账号
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO>>
    * @auther: 贾轶飞
    * @date: 2019/5/6 10:16
    */
    @PostMapping("/queryCart")
    public ResponseData<List<GdOrdershopDTO>> queryCart(RequestData<String> useraccount);

    /** 功能描述:
    * 根据用户购物车中商品编号删除
    * @param: [cartid]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/8 11:15
    */
    @PostMapping("/delCartGoods")
    public ResponseData<Integer> delCartGoods(RequestData<Integer> cartid);

    /** 功能描述:
    *  添加购物车中商品
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/8 11:17
    */
    @PostMapping("/addCartGoods")
    public ResponseData<Integer> addCartGoods(RequestData<GdShoppingCartDTO> requestData);

    /** 功能描述:
    *  修改购物车已有商品的数量
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/8 11:18
    */
    @PostMapping("/updCartGoods")
    public ResponseData<Integer> updCartGoods(RequestData<GdShoppingCartDTO> requestData);

}
