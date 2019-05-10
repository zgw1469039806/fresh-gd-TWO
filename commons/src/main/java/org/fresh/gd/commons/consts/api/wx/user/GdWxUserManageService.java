package org.fresh.gd.commons.consts.api.wx.user;

import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.user.UserAddressDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/9 15:36
 */
@RequestMapping("/GdWxUserManageService")
public interface GdWxUserManageService {

    /**
     * 功能描述:
     * 查询微信小程序用户自定义的收获地址
     * @param: [useraccount]用户账号
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List   <   org.fresh.gd.commons.consts.pojo.dto.user.UserAddressDTO>>
     * @auther: 贾轶飞
     * @date: 2019/5/9 15:44
     */
    @PostMapping("/userAddress")
    public ResponseData<List<UserAddressDTO>> queryUserAddress(RequestData<String> requestData);

    /** 功能描述:
    * 移除用户定义的收获地址
    * @param: [requestData]用户账号
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/9 15:48
    */
    @PostMapping("/removeAddress")
    public ResponseData<Integer> delUserAddress(RequestData<UserAddressDTO> requestData);

    /** 功能描述:
    * 添加用户的收货地址
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/9 15:49
    */
    @PostMapping("/addAddress")
    public ResponseData<Integer> addUserAddress(RequestData<UserAddressDTO> requestData);

    /** 功能描述:
    * 编辑用户的收获地址
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
    * @auther: 贾轶飞
    * @date: 2019/5/9 15:49
    */
    @PostMapping("/updAddress")
    public ResponseData<Integer> updUserAddress(RequestData<UserAddressDTO> requestData);
}
