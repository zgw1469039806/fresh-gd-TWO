package org.gd.vip.impl;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.fresh.gd.commons.consts.api.vip.VipService;
import org.fresh.gd.commons.consts.consts.Consts;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.user.UserAndVipDTO;
import org.fresh.gd.commons.consts.pojo.dto.vip.*;
import org.fresh.gd.commons.consts.utils.PageBean;
import org.fresh.gd.commons.consts.utils.VeDate;
import org.gd.vip.entity.GdVip;
import org.gd.vip.entity.GdVipindetailed;
import org.gd.vip.mapper.GdVipInSetMapper;
import org.gd.vip.mapper.GdVipMapper;
import org.gd.vip.mapper.GdVipindetailedMapper;
import org.gd.vip.mapper.GdViplvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author 贾轶飞
 * @date 2019/4/26 13:19
 */
@Slf4j
@RestController
public class VipServiceImpl implements VipService {
    @Autowired
    GdVipMapper gdVipMapper;

    @Autowired
    GdVipInSetMapper gdVipInSetMapper;

    @Autowired
    GdViplvMapper gdViplvMapper;

    @Autowired
    GdVipindetailedMapper gdVipindetailedMapper;


    @Override
    public ResponseData<UserAndVipDTO> selectOne(@RequestBody RequestData<UserAndVipDTO> requestData) {
        ResponseData<UserAndVipDTO> responseData = new ResponseData<>();
        responseData.setData(gdVipMapper.selevtOne(requestData.getData().getUserId()));
        return responseData;
    }

    /**
     * 功能描述:
     * 新增会员
     *
     * @param dtogdAddVipDTO
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/4/28 13:47
     */
    @Override
    public ResponseData<Integer> addVip(@RequestBody RequestData<GdAddVipDTO> dtogdAddVipDTO) {
        ResponseData<Integer> responseData = new ResponseData<>();
        GdAddVipDTO dtovip = dtogdAddVipDTO.getData();
        Random re = new Random();
        int i = re.nextInt(10000);
        GdVip vip = new GdVip();
        //会员卡号随机数  当前时间加上随机数
        vip.setVipId((new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())) + i);
        vip.setUserId(null);
        vip.setVipbalance(dtovip.getVipbalance());
        vip.setVipeportTime(null);
        vip.setViplv(dtovip.getViplv());
        vip.setVipintegral(dtovip.getVipintegral());
        vip.setVipName(dtovip.getVipName());
        vip.setVipphone(dtovip.getVipphone());
        vip.setVipreport(0);
        vip.setVipStartTime(VeDate.getStringDate());
        Integer in = gdVipMapper.addVip(vip);
        if (in > 0) {
            return responseData;
        }
        responseData.setCode(Consts.Result.BIZ_ERROR.getCode());
        return responseData;
    }

    /**
     * 功能描述:
     * 分页显示会员信息
     *
     * @param pageVipdto
     * @param: [pageVipdto]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.utils.PageBean                               <                               org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO>>
     * @auther: Mr.Xia
     * @date: 2019/4/29 15:43
     */
    @Override
    public ResponseData<PageBean<VipPageDTO>> selPageListVip(@RequestBody RequestData<SelPageVipDTO> pageVipdto) {
        ResponseData<PageBean<VipPageDTO>> responseData = new ResponseData<>();
        //传过来的参数
        SelPageVipDTO selPageVipDTO = pageVipdto.getData();

        if (selPageVipDTO.getPageNo() == 0) {
            responseData.setCode(Consts.Result.ERROR_PARAM.getCode());
            return responseData;
        }

        selPageVipDTO.setPageNo(selPageVipDTO.getPageNo() - 1);
        //查到的vip列表数据
        List<VipPageDTO> vipPageDTO = gdVipMapper.selPageListVip(selPageVipDTO);

        //查询总数
        Integer count = this.selPageCountVip(selPageVipDTO.getVipName(), selPageVipDTO.getViplv());

        //计算总页数
        Integer countPage = 0;
        if (count % selPageVipDTO.getPageSize() == 0) {
            countPage = count / selPageVipDTO.getPageSize();
        } else {
            countPage = count / selPageVipDTO.getPageSize() + 1;
        }


        PageBean<VipPageDTO> pageDTOPageBean = new PageBean<>();
        pageDTOPageBean.setTotalCount(count);
        pageDTOPageBean.setList(vipPageDTO);
        pageDTOPageBean.setTotalPage(countPage);
        pageDTOPageBean.setCurrPage(selPageVipDTO.getPageNo());
        pageDTOPageBean.setPageSize(selPageVipDTO.getPageSize());

        responseData.setData(pageDTOPageBean);

        return responseData;
    }

    /**
     * 功能描述:
     * 条件查询vip总数
     *
     * @auther: Mr.Xia
     * @date: 2019/4/29 16:05
     */
    @Override
    public Integer selPageCountVip(String vipName, Integer viplv) {
        return gdVipMapper.selPageCountVip(vipName, viplv);
    }

    /**
     * 功能描述:
     * 根据会员编号删除会员
     *
     * @param vipId
     * @param: [vipId]
     * @return: java.lang.Integer
     * @auther: Mr.Xia
     * @date: 2019/5/6 8:43
     */
    @Override
    public ResponseData<String> delVipById(@RequestBody RequestData<String> vipId) {
        ResponseData<String> responseData = new ResponseData<>();
        Integer i = gdVipMapper.delVipById(vipId.getData());

        if (i > 0) {
            responseData.setMsg("删除成功！");
            return responseData;
        }

        responseData.setCode(Consts.Result.BIZ_ERROR.getCode());
        return responseData;
    }

    /**
     * 功能描述:
     * 根据会员编号查询会员
     *
     * @param vipId
     * @param: [vipId]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO>
     * @auther: Mr.Xia
     * @date: 2019/5/6 9:35
     */
    @Override
    public ResponseData<VipPageDTO> selOneVipById(@RequestBody RequestData<String> vipId) {
        ResponseData<VipPageDTO> responseData = new ResponseData<>();
        responseData.setData(gdVipMapper.selOneVipById(vipId.getData()));
        return responseData;
    }

    /**
     * 功能描述:
     * 修改会员
     *
     * @param vipUpdDTORequestData
     * @param: [vipUpdDTORequestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/6 10:15
     */
    @Override
    public ResponseData<Integer> updOneVip(@RequestBody RequestData<VipUpdDTO> vipUpdDTORequestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        Integer i = gdVipMapper.updOneVip(vipUpdDTORequestData.getData());
        if (i > 0) {
            responseData.setMsg("修改成功");
            return responseData;
        }
        responseData.setCode(Consts.Result.BIZ_ERROR.getCode());
        return responseData;
    }

    /**
     * 功能描述:
     * 根据会员手机号查询会员信息
     *
     * @param vipphone
     * @param: [vipphone]
     * @return: org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO
     * @auther: Mr.Xia
     * @date: 2019/5/8 15:24
     */
    @Override
    public ResponseData<VipPageDTO> selOneVipByPhone(@RequestBody RequestData<String> vipphone) {
        ResponseData<VipPageDTO> responseData = new ResponseData<>();
        VipPageDTO vipPageDTO = gdVipMapper.selOneVipByPhone(vipphone.getData());
        if (vipPageDTO != null) {
            if (vipPageDTO.getVipreport() == 1) {
                responseData.setMsg("此会员已挂失！");
                responseData.setCode(Consts.Result.BIZ_ERROR.getCode());
                return responseData;
            }
        }
        responseData.setData(vipPageDTO);
        return responseData;
    }

    /**
     * 功能描述:
     * 增加会员积分  会员购买物品增加积分
     *
     * @param vipId
     * @param storeid
     * @param ordermoney
     * @param: [vipphone, viplv, vipintegral]
     * @return: java.lang.Integer
     * @auther: Mr.Xia
     * @date: 2019/5/10 14:08
     */
    @Transactional
    @Override
    public Integer upgVipIntegral(String vipId, Integer storeid, String ordermoney) {

        //1、根据会员手机号获取会员信息
        System.out.println(vipId + "-" + storeid + "-" + ordermoney);

        VipPageDTO vip = gdVipMapper.selOneVipByPhone(vipId);

        //2、根据店铺编号获取该店铺增加积分规则
        VipInSetDTO vipInSetDTO = gdVipInSetMapper.selVipInSetById(storeid);

        //3、计算需要增加的积分（如果达到升级会员的标准需要进行会员升级）修改会员信息 等级不能超过5级 积分不能超过5000
        Integer addVipIntegral = (int) (Double.parseDouble(ordermoney) / Double.parseDouble(vipInSetDTO.getVipinsetmoney()) * vipInSetDTO.getVipinsetgetin());
        //若不满足积分规则 那么直接返回
        if (addVipIntegral < 1) {
            return 1;
        }

        //会员新等级  若当前会员等价为最高级 则只加积分不升级
        Integer newVipLv = vip.getViplv();
        if (vip.getViplv() < 5) {
            //当前会员升级所需积分 先根据当前会员等级+1查询升级下一级会员所需的积分
            Integer i2 = gdViplvMapper.selVipLvByViplv(vip.getViplv() + 1).getVipintegration();
            //会员新等级 在用当前计算出的积分加上会员原来的积分对升级所需积分进行比较 若大于则会员等级加1
            newVipLv = Integer.parseInt(vip.getVipintegral() + addVipIntegral) > i2 ? newVipLv + 1 : newVipLv;
        }
        //修改会员积分
        Integer i = gdVipMapper.updOneByVipPhone(vipId, newVipLv, addVipIntegral + "");

        //4、会员积分明细表增加一条记录
        GdVipindetailedDTO gdVipindetailedDTO = new GdVipindetailedDTO();
        gdVipindetailedDTO.setStoreid(storeid);
        gdVipindetailedDTO.setVipindetailednum(addVipIntegral);
        gdVipindetailedDTO.setVipindetailedtype("获得积分");
        gdVipindetailedDTO.setVipPhone(vipId);

        Integer integer = gdVipindetailedMapper.addVipindetailed(gdVipindetailedDTO);
        return i;
    }

    /**
     * 功能描述:
     * 根据手机号查询此会员是否存在
     *
     * @param phone
     * @param: [phone]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/20 10:32
     */
    @Override
    public Integer selOneByVipPhone(String phone) {
        return gdVipMapper.selOneByVipPhone(phone);
    }

    @Transactional
    @Override
    public ResponseData<Integer> updVipUserId(@RequestBody RequestData<VipBindUserId> vipBindUserId) {
        ResponseData<Integer> responseData = new ResponseData<>();
        Integer i = selOneByVipPhone(vipBindUserId.getData().getPhone());
        if (i < 1) {
            responseData.setCode(Consts.Result.BIZ_ERROR.getCode());
            responseData.setMsg("该手机号不存在！");
            return responseData;
        }
        gdVipMapper.updVipUserId(vipBindUserId.getData());
        return responseData;
    }

    /**
     * 功能描述:
     * 根据会员手机号修改会员余额
     *
     * @param vipphone
     * @param vipbalance
     * @param: [vipphone, vipbalance]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/20 14:39
     */
    @Transactional
    @Override
    public Integer updVipBalanceByVipPhone(String vipphone, String vipbalance, Integer storeId) {
        //todo:做会员余额是否充足判断 如果不充足，返回1000
        Integer i = gdVipMapper.updVipBalanceByVipPhone(vipphone, vipbalance);

        GdVipindetailedDTO gdVipindetailedDTO = new GdVipindetailedDTO();
        gdVipindetailedDTO.setStoreid(storeId);
        gdVipindetailedDTO.setVipmoney(vipbalance);
        gdVipindetailedDTO.setVipindetailednum(0);
        gdVipindetailedDTO.setVipindetailedtype("余额消费");
        gdVipindetailedDTO.setVipPhone(vipphone);
        gdVipindetailedMapper.addVipindetailed(gdVipindetailedDTO);
        return i;
    }

    /**
    *
    * 功能描述:
    *   根据手机号或用户id查询会员信息
    * @param: [vipSelVipDTO]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.vip.VipPageDTO>
    * @auther: Mr.Xia
    * @date: 2019/5/22 10:47
    */
    @Override
    public ResponseData<VipPageDTO> selVipByVipPhoneAndUserId(@RequestBody RequestData<VipSelVipDTO> vipSelVipDTO) {
        ResponseData<VipPageDTO> responseData = new ResponseData<>();
        VipPageDTO VipPageDTO = gdVipMapper.selVipByVipPhoneAndUserId(vipSelVipDTO.getData());
        responseData.setData(VipPageDTO);
        return responseData;
    }

    /**
     * 功能描述:
     * 会员解除绑定-根据会员手机号
     *
     * @param vipPhone
     * @param: [vipPhone]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: Mr.Xia
     * @date: 2019/5/22 11:14
     */
    @Override
    public ResponseData<Integer> updRemoveUserId(@RequestBody RequestData<String> vipPhone) {
        ResponseData<Integer> responseData = new ResponseData<>();
        gdVipMapper.updRemoveUserId(vipPhone.getData());
        return responseData;
    }
}
