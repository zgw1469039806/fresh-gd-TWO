package org.fresh.gd.commons.consts.api.vip;

import org.apache.ibatis.annotations.Param;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.user.UserAndVipDTO;
import org.fresh.gd.commons.consts.pojo.dto.vip.*;
import org.fresh.gd.commons.consts.utils.PageBean;
import org.springframework.web.bind.annotation.*;

/**
 * @author 贾轶飞
 * @dat e2019/4/26 13:21
 */
@RequestMapping("/VipService")
public interface VipService {
    /** 功能描述:
    *      根据会员卡号查询会员信息
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.user.UserAndVipDTO>
    * @auther: 贾轶飞
    * @date: 2019/4/27 9:18
    */
    @PostMapping("/vipSelectOne")
    ResponseData<UserAndVipDTO> selectOne(RequestData<UserAndVipDTO> requestData);

    /**
    *
    * 功能描述:
    *   新增会员
    * @param: [dtogdAddVipDTO]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/4/28 13:47
    */
    @PostMapping("/addVip")
    ResponseData<Integer> addVip(RequestData<GdAddVipDTO > dtogdAddVipDTO);

    /**
    *
    * 功能描述:
    *   分页显示会员信息
    * @param: [pageVipdto]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.utils.PageBean<org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO>>
    * @auther: Mr.Xia
    * @date: 2019/4/29 15:43
    */
    @PostMapping("/selPageListVip")
    ResponseData<PageBean<VipPageDTO>> selPageListVip(RequestData<SelPageVipDTO> pageVipdto);


    /**
    *
    * 功能描述:
    *   条件查询vip总数
    * @param: [vipName, VipLv]
    * @return: java.lang.Integer
    * @auther: Mr.Xia
    * @date: 2019/4/29 16:09
    */
    @PostMapping("/selPageCountVip")
    Integer selPageCountVip(@RequestParam("vipName") String vipName ,@RequestParam("viplv") Integer viplv);


    /**
    *
    * 功能描述:
    *   根据会员编号删除会员
    * @param: [vipId]
    * @return: java.lang.Integer
    * @auther: Mr.Xia
    * @date: 2019/5/6 8:43
    */
    @PostMapping("/delVipById")
    ResponseData<String> delVipById(RequestData<String> vipId);


    /**
    *
    * 功能描述:
    *    根据会员编号查询会员
    * @param: [vipId]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO>
    * @auther: Mr.Xia
    * @date: 2019/5/6 9:35
    */
    @PostMapping("/selOneVipById")
    ResponseData<VipPageDTO> selOneVipById(RequestData<String> vipId);

    /**
    *
    * 功能描述:
    *   修改会员
    * @param: [vipUpdDTORequestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/6 10:15
    */
    @PostMapping("/updOneVip")
    ResponseData<Integer> updOneVip(RequestData<VipUpdDTO> vipUpdDTORequestData);


    /**
    * 功能描述:
    *   根据会员手机号查询会员信息
    * @param: [vipphone]
    * @return: org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO
    * @auther: Mr.Xia
    * @date: 2019/5/8 15:24
    */
    @PostMapping("/selOneVipByPhone")
    ResponseData<VipPageDTO> selOneVipByPhone(RequestData<String> vipphone);

    /**
    *
    * 功能描述:
    *   增加会员积分  会员购买物品增加积分
    * @param: [vipphone, viplv, vipintegral]
    * @return: java.lang.Integer
    * @auther: Mr.Xia
    * @date: 2019/5/10 14:08
    */
    @PostMapping("/upgVipIntegral")
    Integer upgVipIntegral(@RequestParam("vipId") String vipId , @RequestParam("storeid") Integer storeid , @RequestParam("ordermoney") String ordermoney);

    /**
    *
    * 功能描述:
    *   根据手机号查询此会员是否存在
    * @param: [phone]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/20 10:32
    */
    @PostMapping("/selOneByVipPhone")
    Integer selOneByVipPhone(String phone);

    @PostMapping("/updVipUserId")
    ResponseData<Integer> updVipUserId(RequestData<VipBindUserId> vipBindUserId);

    /**
    * 功能描述:
    *   根据会员手机号修改会员余额
    * @param: [vipphone, vipbalance]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/20 14:39
    */
    @PostMapping("/updVipBalanceByVipPhone")
    Integer updVipBalanceByVipPhone(@RequestParam("vipphone") String vipphone , @RequestParam("vipbalance") String vipbalance , @RequestParam("storeId") Integer storeId);

    /**
    *
    * 功能描述:
    *   根据手机号或用户id查询会员信息
    * @param: [vipSelVipDTO]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO>
    * @auther: Mr.Xia
    * @date: 2019/5/22 10:47
    */
    @PostMapping("/selVipByVipPhoneAndUserId")
    ResponseData<VipPageDTO> selVipByVipPhoneAndUserId(RequestData<VipSelVipDTO> vipSelVipDTO);

    /**
    *
    * 功能描述:
    *   会员解除绑定-根据会员手机号
    * @param: [vipPhone]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: Mr.Xia
    * @date: 2019/5/22 11:14
    */
    @PostMapping("/updRemoveUserId")
    ResponseData<Integer> updRemoveUserId(RequestData<String> vipPhone);

}
