package org.fresh.gd.commons.consts.pojo.dto.order;

import com.alipay.api.domain.ExtendParams;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel("阿里当面付")
public class AliFicePay {
    // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
    // 需保证商户系统端不能重复，建议通过数据库sequence生成，
    String outTradeNo = UUID.randomUUID().toString();

    // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
    String subject;

    // (必填) 订单总金额，单位为元，不能超过1亿元
    // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
    String totalAmount;

    // (必填) 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码
    String authCode; // 条码示例，286648048691290423
    // (可选，根据需要决定是否使用) 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
    // 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
    //        String discountableAmount = "1.00"; //

    // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
    // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
    String undiscountableAmount;

    // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
    // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
    String sellerId = "";

    // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
    String body = "购买商品3件共20.00元";

    // 商户操作员编号，添加此参数可以为商户操作员做销售统计
    String operatorId = "test_operator_id";

    // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
    String storeId = "test_store_id";

    // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
    String providerId = "2088100200300400500";
    //构造函数吧providerId赋值进去
    ExtendParams extendParams = new ExtendParams();

    // 支付超时，线下扫码交易定义为5分钟
    String timeoutExpress;

    // 商品明细列表，需填写购买商品详细信息，
//    List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
    String appAuthToken = "cnmd";//根据真实值填写

    public AliFicePay(String subject, String totalAmount, String authCode, String operatorId, String storeId) {
        this.subject = subject;
        this.totalAmount = totalAmount;
        this.authCode = authCode;
        this.undiscountableAmount = "0.0";
        this.body = "格调生鲜商品交易";
        this.operatorId = operatorId;
        this.storeId = storeId;
        this.timeoutExpress = "5m";

    }
}
