package org.gd.order.impl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO;
import org.fresh.gd.commons.consts.pojo.dto.vip.VipSelVipDTO;
import org.fresh.gd.commons.consts.utils.PageBean;
import org.fresh.gd.commons.consts.utils.VeDate;
import org.gd.order.entity.GdOrder;
import org.gd.order.entity.GdOrdershop;
import org.gd.order.entity.GdReportform;
import org.gd.order.entity.GdShoppingcart;
import org.gd.order.fegin.OrderFeginToGoods;
import org.gd.order.fegin.OrderFeginToShopping;
import org.gd.order.fegin.OrderFeginToVip;
import org.gd.order.mapper.GdOrderMapper;
import org.gd.order.mapper.GdOrdershopMapper;
import org.gd.order.mapper.GdReportformMapper;
import org.gd.order.mapper.GdShoppingcartMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private GdReportformMapper gdReportformMapper;

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
//    @LcnTransaction
    @Transactional
    @Override
    public ResponseData<List> insertOrder(@RequestBody RequestData<GdOrderDTO> gdOrderDTORequestData) {
        GdOrderDTO gdOrderDTO = new GdOrderDTO();
        BeanUtils.copyProperties(gdOrderDTORequestData.getData(), gdOrderDTO);
        RequestData<List<GdComdityparticularDTO>> requestData = new RequestData<>();
        List<GdComdityparticularDTO> list = new ArrayList<>();
        ResponseData<List> responseData = new ResponseData<>();
        for (GdOrdershopDTO dto : gdOrderDTORequestData.getData().getTableData()) {
            GdComdityparticularDTO gdComdityparticularDTO = new GdComdityparticularDTO();
            gdComdityparticularDTO.setComdityId(dto.getComdityId());
            gdComdityparticularDTO.setComdnum(dto.getNum());
            list.add(gdComdityparticularDTO);
        }
        requestData.setData(list);
        //如果交易场景为线上
        if (gdOrderDTORequestData.getData().getOrderscene() == 0) {
            //判断库存
            responseData = orderFeginToShopping.editStock(requestData);
            if (responseData.getMsg().equals("库存不足")) {
                return responseData;
            }
        } else {
            //如果是会员余额支付
            if (gdOrderDTORequestData.getData().getOrdermeans() == 1) {
                Integer upd = orderFeginToVip.updVipBalanceByVipPhone(gdOrderDTO.getVipId(), gdOrderDTO.getOrdermoney(), gdOrderDTO.getStoreid());
                //如果余额不足
                if (upd == 1000) {
                    responseData.setMsg("余额不足");
                    return responseData;
                }
                //如果支付手段为支付宝
            } else if (gdOrderDTORequestData.getData().getOrdermeans() == 2) {
                AliFicePay aliFicePay = new AliFicePay("格调生鲜商品交易", gdOrderDTO.getOrdermoney().toString(), gdOrderDTO.getFukuanma(), "1", gdOrderDTO.getStoreid().toString());
                Integer msg = jiahengxiaoxixi(aliFicePay);
                if (msg==0){
                    throw new BizException("支付有误");
                }
            }
        }
        GdOrder gdOrder = new GdOrder();
        BeanUtils.copyProperties(gdOrderDTORequestData.getData(), gdOrder);

        //随机单号
        Random re = new Random();
        int i = re.nextInt(10000);
        gdOrder.setOrderid((new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())) + i);
        gdOrder.setOrderStat(gdOrderDTORequestData.getData().getOrderStat());
        gdOrder.setOrderTime(VeDate.getStringDate());
        //插入订单
        int save = gdOrderMapper.insert(gdOrder);
        //订单创建成功，清空选中商品加入订单中的商品
        if (save > 0) {
            List<GdOrdershopDTO> list1 = gdOrderDTORequestData.getData().getTableData();
            for (GdOrdershopDTO dto : list1
            ) {
                Integer p = gdShoppingcartMapper.batchDelCart(dto.getComdityId(), gdOrderDTORequestData.getData()
                        .getUserId());
                if (p == 0) {
                    responseData.setMsg("清除购物车失败");
                    break;
                }
            }
        }

        ResponseData responseData1 = orderFeginToShopping.reduceStock(requestData);//减少库存
        if (responseData1.getCode() == 1000) {//如果减少成功
            for (GdOrdershopDTO dto : gdOrderDTORequestData.getData().getTableData()) {
                dto.setOrderid(gdOrder.getOrderid());
            }
            gdOrdershopMapper.insertOrderShop(gdOrderDTORequestData.getData().getTableData());
        } else {
            throw new BizException("库存减扣失败");
        }
        //增加积分
        if (gdOrderDTO.getVipId() != null && gdOrderDTO.getVipId() != "" && gdOrderDTO.getVipId().equals("")) {
            String str = gdOrderDTORequestData.getData().getOrdermoney().trim();
            Integer i1 = orderFeginToVip.upgVipIntegral(gdOrderDTORequestData.getData().getVipId().trim(), gdOrderDTORequestData.getData().getStoreid(), str);
        }
        //增加订单成本
        GdReportform gdReportform = new GdReportform();
        BeanUtils.copyProperties(gdOrder, gdReportform);
        gdReportform.setRfmoney(gdOrderDTO.getRfmoney());
        gdReportformMapper.insert(gdReportform);
        responseData.setMsg(gdOrder.getOrderid());
        responseData.setCode(Consts.Result.SUCCESS.getCode());
        return responseData;
    }

    /**
     * 功能描述
     * 根据用户id信息 查询购物车商品
     *
     * @param requestData
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List < org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO>>
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

    /**
     * 功能描述:
     * 用户订单
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.order.GdUserOrderDTO>
     * @auther: 贾轶飞
     * @date: 2019/5/23 10:15
     */
    @Override
    public ResponseData<List<GdUserOrderDTO>> userOrderQuery(@RequestBody RequestData<GdUserOrderDTO> requestData) {
        ResponseData<List<GdUserOrderDTO>> responseData = new ResponseData<>();
        List<GdUserOrderDTO> gdUserOrderDTO = gdOrderMapper.userOrderQuery(requestData.getData());
        List<Integer> list = new ArrayList<>();
        for (GdUserOrderDTO dto : gdUserOrderDTO) {
            //根据订单编号查询订单详细
            dto.setTable(gdOrdershopMapper.selOrderShopById(dto.getOrderid()));
            for (GdOrdershopDTO shopdto : dto.getTable()) {
                list.add(shopdto.getComdityId());
            }
            ResponseData<List<GdCommodityDTO>> rq = gdCommodityService.QueryShopByIds(list);
            for (GdOrdershopDTO shop : dto.getTable()) {
                for (GdCommodityDTO commodityDTO : rq.getData()) {
                    if (shop.getComdityId() == commodityDTO.getComdityId()) {
                        shop.setImageUrl(commodityDTO.getImagesurl());
                        shop.setComdityName(commodityDTO.getComdityname());
                        break;
                    }
                }
            }
        }


        responseData.setData(gdUserOrderDTO);
        return responseData;
    }

    /**
     * 功能描述:
     * 根据订单编号修改订单状态为已支付（用于小程序订单支付）
     *
     * @param orderId
     * @param: [orderStartDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/24 10:03
     */
    @Override
    public ResponseData<Integer> updOrderStartPay(String orderId) {
        ResponseData<Integer> responseData = new ResponseData<>();
        OrderStartDTO orderStartDTO = new OrderStartDTO();
        orderStartDTO.setOrderId(orderId);
        orderStartDTO.setOrdStart(1);
        gdOrderMapper.updOrderStartById(orderStartDTO);
        return responseData;
    }

    /**
     * 功能描述:
     * 线上-根据订单编号修改订单状态为已完成（用于小程序订单点击确认收货）
     *
     * @param orderId
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/24 10:18
     */
    @Override
    public ResponseData<Integer> updOrderStartOK(String orderId) {
        ResponseData<Integer> responseData = new ResponseData<>();
        OrderStartDTO orderStartDTO = new OrderStartDTO();
        orderStartDTO.setOrderId(orderId);
        orderStartDTO.setOrdStart(4);
        gdOrderMapper.updOrderStartById(orderStartDTO);
        return responseData;
    }

    /**
     * 功能描述:
     * 线上-根据订单编号修改订单状态为申请退款（用于小程序点击申请退款）
     *
     * @param orderId
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/24 11:17
     */
    @Override
    public ResponseData<Integer> updOrderStartTuiPay(String orderId) {
        ResponseData<Integer> responseData = new ResponseData<>();
        OrderStartDTO orderStartDTO = new OrderStartDTO();
        orderStartDTO.setOrderId(orderId);
        orderStartDTO.setOrdStart(5);
        gdOrderMapper.updOrderStartById(orderStartDTO);
        return responseData;
    }

    /**
     * 功能描述:
     * 线上-根据订单编号修改订单状态为到店支付（用于小程序点击到店支付）
     *
     * @param orderId
     * @param: [orderId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/24 11:20
     */
    @Override
    public ResponseData<Integer> updOrderStartToGoodsPay(String orderId) {
        ResponseData<Integer> responseData = new ResponseData<>();
        OrderStartDTO orderStartDTO = new OrderStartDTO();
        orderStartDTO.setOrderId(orderId);
        orderStartDTO.setOrdStart(8);
        gdOrderMapper.updOrderStartById(orderStartDTO);
        return responseData;
    }

    /**
     * @Description 统计报表
     * @Date: 11:48 2019/5/24
     * @Author: 郭家恒
     */
    @Override
    public ResponseData<List<Float>> QueryDeportForm(@RequestBody GdDeportFormDTO gdDeportFormDTO) {
        Map<String, List<GdOrder>> map = new HashMap<>();
        List<GdDeportFormDTO> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            gdDeportFormDTO.setMones(i + "");
            //拿到指定年份中第i个月的指定门店的订单(订单包括营业额)
            map.put(i + "", gdOrderMapper.SelOrderByYear(gdDeportFormDTO.getSelyear(), i + "", gdDeportFormDTO.getStoreid()));
            //拿到指定年份第i月的指定门店的营业额
            GdDeportFormDTO gdDeportFormDTO1 = gdOrderMapper.QueryDeportForm(gdDeportFormDTO);
            if (gdDeportFormDTO1 == null) {
                gdDeportFormDTO1 = new GdDeportFormDTO();
            }
            list.add(gdDeportFormDTO1);
        }
        //用于计算每个月的利润
        List<Float> lirun = new ArrayList<>();
        //遍历每个月的订单
        for (int i = 1; i <= map.size(); i++) {
            //得到第i个月的订单
            List<GdOrder> list1 = map.get(i + "");
            float yingli = 0;
            //遍历第i个月的订单
            for (GdOrder gdOrder : list1) {
                //如果当前这比订单销售额不为空
                Wrapper<GdReportform> wrapper = new QueryWrapper<>();
                ((QueryWrapper<GdReportform>) wrapper).eq("orderid", gdOrder.getOrderid());
                GdReportform gdReportform = gdReportformMapper.selectOne(wrapper);
                if (gdReportform.getRfmoney() > 0) {
                    //计算第i个月的成本
                    yingli += Float.valueOf(gdReportform.getRfmoney());
                }
            }
            //拿到第i个月的营业额
            GdDeportFormDTO gdDeportFormDTO1 = list.get(i - 1);
            //盈利-成本
            float maolirun = gdDeportFormDTO1.getSummoney() - yingli;
            lirun.add(maolirun);
        }
        ResponseData<List<Float>> responseData = new ResponseData<>();
        responseData.setData(lirun);
        return responseData;
    }

    @Override
    public ResponseData<Map<String, Integer>> queryCountOrder(@RequestBody RequestData<Integer> requestData) {
        ResponseData<Map<String, Integer>> responseData = new ResponseData<>();
        Map<String, Integer> map = new HashMap<>();
        Integer[] i = {0, 1, 2, 3, 4};
        Integer x;
        for (int j = 0; j < i.length; j++) {
            if (i[j] == 0) {
                x = gdOrderMapper.queryCountOrder(requestData.getData(), i[j]);
                map.put("pay_num", x);
            } else if (i[j] == 1) {
                x = gdOrderMapper.queryCountOrder(requestData.getData(), i[j]);
                map.put("rec_num", x);
            } else if (i[j] == 2) {
                x = gdOrderMapper.queryCountOrder(requestData.getData(), i[j]);
                map.put("finish_num", x);
            } else if (i[j] == 3) {
                x = gdOrderMapper.queryCountOrder(requestData.getData(), i[j]);
                map.put("refund_num", x);
            } else if (i[j] == 4) {
                x = gdOrderMapper.queryCountOrder(requestData.getData(), i[j]);
                map.put("HasSum", x);
            }
        }
        responseData.setData(map);
        return responseData;
    }

    /**
     * 功能描述:
     * 支付
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 贾轶飞
     * @date: 2019/5/25 17:54
     */
    @Override
    public ResponseData<Integer> payOrder(@RequestBody RequestData<GdUserOrderDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        RequestData<VipSelVipDTO> vipSelVipDTO = new RequestData<>();
        VipSelVipDTO vip = new VipSelVipDTO();
        vip.setUserId(requestData.getData().getUserId());
        vipSelVipDTO.setData(vip);
        ResponseData<VipPageDTO> responseData1 = orderFeginToVip.selVipByVipPhoneAndUserId(vipSelVipDTO);

        if (Float.parseFloat(responseData1.getData().getVipbalance()) < Float.parseFloat(requestData.getData()
                .getOrdermoney())) {
            responseData.setMsg("账户余额不足");
            return responseData;
        }

        Integer s = orderFeginToVip.updVipBalanceByVipPhone(requestData.getData().getPhone(), requestData.getData().getOrdermoney()
                , requestData.getData().getStoreid());
        if (s > 0) {
            Integer i = gdOrderMapper.updPayOrder(requestData.getData());
            if (i > 0) {
                responseData.setMsg("订单支付完成");
            } else {
                responseData.setMsg("订单支付失败");
            }
            responseData.setData(i);
        }

        return responseData;
    }

    /**
     * 功能描述:
     * 取消订单
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 贾轶飞
     * @date: 2019/5/25 18:51
     */
    @Override
    public ResponseData<Integer> removeOrder(@RequestBody RequestData<String> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        Integer i = 0;
        Integer detail = gdOrdershopMapper.removeOrderGoods(requestData.getData());
        if (detail > 0) {
            i = gdOrderMapper.removeOrder(requestData.getData());
            responseData.setMsg("订单取消成功！");
        } else {
            List<GdOrdershopDTO> s = gdOrdershopMapper.selOrderShopById(requestData.getData());
            if (s.size() > 0) {
                detail = gdOrdershopMapper.removeOrderGoods(requestData.getData());
            }
            i = gdOrderMapper.removeOrder(requestData.getData());
            responseData.setMsg("订单已取消,请稍后查看。");
        }
        responseData.setData(i);
        return responseData;
    }

    /** 功能描述:
    * 配送订单根据订单
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.order.GdUserOrderDTO>>
    * @auther: 贾轶飞
    * @date: 2019/6/5 14:02
    */
    @Override
    public ResponseData<List<GdUserOrderDTO>> dispatchingOrder(@RequestBody RequestData<GdUserOrderDTO> requestData) {


        ResponseData<List<GdUserOrderDTO>> responseData=new ResponseData<>();
        responseData.setData(gdOrderMapper.userOrderQuery(requestData.getData()));
        return responseData;
    }

    @Override
    public Integer jiahengxiaoxixi(AliFicePay aliFicePay) {
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        StringBuffer bizCotent = new StringBuffer("{" +
                "\"out_trade_no\":\"" + aliFicePay.getOutTradeNo() + "\"," +
                "\"auth_code\":\"" + aliFicePay.getAuthCode() + "\"," +
                "\"subject\":\"" + "xxx品牌xxx门店当面付消费" + "\"," +//订单标题粗略描述用户的支付目的
                "\"total_amount\":" + aliFicePay.getTotalAmount() + "," +//总金额
                // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
                "\"operator_id\":\"yx_001\"," +//商户操作员编号
                "\"store_id\":\"NJ_001\"," +//商户门店编号
                "\"timeout_express\":\"90m\"," +
                "\"scene\":\"bar_code\"" +
                "  }");
        request.setBizContent(bizCotent.toString());
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", Consts.AliPayConfig.appid, Consts.AliPayConfig.private_key, "json", "GBK", Consts.AliPayConfig.alipay_public_key, Consts.AliPayConfig.sign_type);
        Integer msg = 0;
        try {
            AlipayTradePayResponse response = alipayClient.execute(request);
            System.out.println("response-------------------------------");
            System.out.println(response);
            if (response.getMsg() == "Success") {
                msg = 1;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
