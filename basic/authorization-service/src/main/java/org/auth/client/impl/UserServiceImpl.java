package org.auth.client.impl;

import com.codingapi.tx.annotation.TxTransaction;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.auth.client.entity.GdRole;
import org.auth.client.entity.GdUser;
import org.auth.client.fegin.ManageFeignService;
import org.auth.client.mapper.GdRoleMapper;
import org.auth.client.mapper.GdTakedeliveryMapper;
import org.auth.client.mapper.GdUserMapper;
import org.fresh.gd.commons.consts.api.auth.UserService;
import org.fresh.gd.commons.consts.consts.Consts;
import org.fresh.gd.commons.consts.exceptions.BizException;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogHdDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.oauth.GdPositionDTO;
import org.fresh.gd.commons.consts.pojo.dto.oauth.UserDTO;
import org.fresh.gd.commons.consts.pojo.dto.user.GdTakedeliveryDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @DATA 2019-04-19 14:10
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    GdPositionServiceImpl gdPositionService;

    @Autowired
    GdUserMapper gdUserMapper;

    @Autowired
    GdRoleMapper gdRoleMapper;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    ManageFeignService manageFeignService;

    @Autowired
    GdTakedeliveryMapper gdTakedeliveryMapper;

    @Transactional
    @Override
    public ResponseData<Integer> saveUser(@RequestBody RequestData<UserDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        UserDTO userDTO = requestData.getData();
        System.err.println("----" + userDTO);
        GdUser gdUserTwo = gdUserMapper.selUserAcc(userDTO.getUseraccount());
        if (gdUserTwo != null) {
            throw new BizException("账号不能重复");
        }
        if (StringUtils.isEmpty(userDTO.getPhone())) {
            throw new BizException("手机号不能为空");
        }
        userDTO.setPassword(passwordEncoder.encode("123456"));
        GdUser gdUser = new GdUser();
        BeanUtils.copyProperties(userDTO, gdUser);
        //向用户表插入
        Integer usersave = gdUserMapper.saveUserYg(gdUser);
        if (usersave > 0) {
            GdPositionDTO gdPositionDTO = new GdPositionDTO();
            gdPositionDTO.setUserId(gdUser.getUserId());
            gdPositionDTO.setPname(requestData.getData().getUsername());
            //向权限表插入
            gdPositionService.savaPosn(gdPositionDTO);
            GdRole gdRole = new GdRole();
            gdRole.setRoleid(gdPositionDTO.getUserId());
            gdRole.setRolename(requestData.getData().getUsername());
            gdRoleMapper.insert(gdRole);

            responseData.setMsg(Consts.Result.SUCCESS.getMsg());
            return responseData;
        }
        responseData.setMsg(Consts.Result.BIZ_ERROR.getMsg());
        return responseData;
    }

    /**
     * 功能描述
     * 查询所有员工 以及所在门店
     *
     * @param requestData
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List < org.fresh.gd.commons.consts.pojo.dto.oauth.UserDTO>>
     * @author zgw
     */
    @Override
    public ResponseData<List<UserDTO>> selAllAndByUsername(@RequestBody RequestData<UserDTO> requestData) {
        RequestData<List<UserDTO>> listRequestData = new RequestData<>();
        ResponseData<List<UserDTO>> listResponseData = new ResponseData<>();


        List<UserDTO> userDTOS = gdUserMapper.selYgByMd(requestData.getData().getUseraccount());

        listRequestData.setData(userDTOS);
        ResponseData<List<GdStoreDTO>> gdStoreDTOResponseData = manageFeignService.selByYg(listRequestData);

        List<GdStoreDTO> data = gdStoreDTOResponseData.getData();
        for (UserDTO userDTO : userDTOS) {
            for (GdStoreDTO gdStoreDTO : data) {
                if (userDTO.getIsnoYg().equals(gdStoreDTO.getStoreid().toString())) {
                    userDTO.setGdStoreName(gdStoreDTO.getStorename());
                    userDTO.setStoreaddress(gdStoreDTO.getStoreaddress());
                    break;
                }
            }
        }
        listResponseData.setData(userDTOS);
        return listResponseData;
    }

    /**
     * 功能描述
     * 根据用户名称拿用户ID
     *
     * @param requestData
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List < org.fresh.gd.commons.consts.pojo.dto.oauth.UserDTO>>
     * @author zgw
     */
    @Override
    public ResponseData<List<UserDTO>> selLogByUserId(@RequestBody RequestData<List<GdLogHdDTO>> requestData) {
        ResponseData<List<UserDTO>> responseData = new ResponseData<>();

        List<GdLogHdDTO> logHdDTOS = requestData.getData();
        List<UserDTO> userDTOS = gdUserMapper.selLogByUserName(logHdDTOS);
        responseData.setData(userDTOS);
        return responseData;
    }

    /**
     * 功能描述:
     * 根据地址编号查询地址信息
     *
     * @param addressId
     * @param: [addressId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.user.GdTakedeliveryDTO>
     * @auther: Mr.Xia
     * @date: 2019/5/16 16:31
     */
    @Override
    public ResponseData<GdTakedeliveryDTO> selAddressById(@RequestBody RequestData<Integer> addressId) {
        ResponseData<GdTakedeliveryDTO> responseData = new ResponseData<>();
        GdTakedeliveryDTO gdTakedeliveryDTO = gdTakedeliveryMapper.selAddressById(addressId.getData());
        responseData.setData(gdTakedeliveryDTO);
        return responseData;
    }
}
