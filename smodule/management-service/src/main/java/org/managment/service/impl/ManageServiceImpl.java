package org.managment.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.fresh.gd.commons.consts.api.management.ManageService;
import org.fresh.gd.commons.consts.consts.Consts;
import org.fresh.gd.commons.consts.exceptions.BizException;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;

import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.ManageStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.oauth.UserDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.managment.service.entity.GdStore;
import org.managment.service.entity.GdStoreimage;
import org.managment.service.mapper.GdStoreMapper;
import org.managment.service.mapper.GdStoreimageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @DATA 2019-04-17 14:48
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@RestController
public class ManageServiceImpl implements ManageService {

    @Autowired
    GdStoreMapper gdStoreMapper;

    @Autowired
    GdStoreimageMapper gdStoreimageMapper;

    @Autowired
    ManageImageServiceImpl manageImageService;

    @Transactional
    @Override
    public ResponseData<Integer> inserStore(@RequestBody GdStoreDTO requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        String storename = requestData.getStorename();
        if (StringUtils.isEmpty(storename)) {//判断门店名称
            throw new BizException("门店名称不能为空");
        }
        ManageStoreDTO manageStoreDTO = gdStoreMapper.selByName(storename);//按名字查询门店信息
        if (manageStoreDTO != null) {
            responseData.setMsg("门店名称不能重复");
            throw new BizException("门店名称不能重复");
        }
        GdStore gdStore = new GdStore();
        BeanUtils.copyProperties(requestData, gdStore);
        //存储门店
        Integer save = gdStoreMapper.save(gdStore);
        if (save > 0) {//如果存储成功
            List<ManageStoreDTO> imgs = new ArrayList<>();
            //循环赋值给门店图片list
            for (ManageStoreDTO dto : requestData.getManageStoreDTOList()) {
                dto.setStoreid(gdStore.getStoreid());
                dto.setStoreImages(dto.getStoreImages());
                imgs.add(dto);
            }
            RequestData<List<ManageStoreDTO>> images = new RequestData<>();
            images.setData(imgs);
            //存储门店图片链接
            manageImageService.inserImagesStore(images);
        }
        responseData.setCode(Consts.Result.ERROR_PARAM.getCode());
        return responseData;
    }

    @Override
    public ResponseData<List<GdStoreDTO>> selStroreByName(@RequestBody GdStoreDTO manageStoreDTO) {
        ResponseData<List<GdStoreDTO>> responseData = new ResponseData<>();
        List<GdStoreDTO> manageStoreDTOS = gdStoreMapper.selStoreAndImageByName(manageStoreDTO.getStorename());
        responseData.setData(manageStoreDTOS);
        return responseData;
    }


    @Override
    public ResponseData<Integer> delByIdStro(Integer storeid) {
        ResponseData<Integer> responseData = new ResponseData<>();

        Integer deleteByIdStro = gdStoreMapper.deleteByIdStro(storeid);
        if (deleteByIdStro > 0) {
            responseData.setCode(Consts.Result.SUCCESS.getCode());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<GdStoreDTO>> selByYg(@RequestBody RequestData<List<UserDTO>> requestData) {
        ResponseData<List<GdStoreDTO>> responseData = new ResponseData<>();
        List<UserDTO> data = requestData.getData();
        List<GdStoreDTO> gdStoreDTOS = gdStoreMapper.selStoreAndImageByList(data);
        responseData.setData(gdStoreDTOS);
        return responseData;
    }

    /**
     * 功能描述
     * 根据商品门店ID集合
     *
     * @param gdCommodityDTOList
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO>>
     * @author zgw
     */
    @Override
    public ResponseData<List<GdStoreDTO>> selByssmd(@RequestBody RequestData<List<GdCommodityDTO>> gdCommodityDTOList) {
        ResponseData<List<GdStoreDTO>> listResponseData = new ResponseData<>();
        listResponseData.setData(gdStoreMapper.selSSDPById(gdCommodityDTOList.getData()));
        return listResponseData;
    }

    /**
     * 功能描述:
     * 根据门店ID集合查询
     *
     * @param list
     * @param: [list]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO>>
     * @auther: 郭家恒
     * @date: 2019/4/27 17:21
     */
    @Override
    public ResponseData<List<GdStoreDTO>> QueryByid(@RequestBody RequestData<List<Integer>> list) {
        ResponseData<List<GdStoreDTO>> listResponseData = new ResponseData<>();
        listResponseData.setData(gdStoreMapper.QueryById(list.getData()));
        return listResponseData;
    }

    /**
     * 功能描述:
     * 查询所有门店
     *
     * @param: []
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO>>
     * @auther: 郭家恒
     * @date: 2019/4/28 14:07
     */
    @Override
    public ResponseData<List<GdStoreDTO>> QueryAll(@RequestBody RequestData<String> requestData) {
        ResponseData<List<GdStoreDTO>> responseData = new ResponseData<>();
        responseData.setData(gdStoreMapper.selStoreAndImageByName(requestData.getData()));
        return responseData;
    }

    /**
     * 功能描述:
     * 修改门店信息
     *
     * @param requestData
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/10 13:55
     */
    @Transactional
    @Override
    public ResponseData<Integer> updManna(@RequestBody RequestData<GdStoreDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        GdStore gdStore = new GdStore();
        BeanUtils.copyProperties(requestData.getData(), gdStore);
        Wrapper<GdStore> wrapper = new QueryWrapper<>();
        ((QueryWrapper<GdStore>) wrapper).eq("storeid", gdStore.getStoreid());
        gdStoreMapper.update(gdStore, wrapper);
        Wrapper<GdStoreimage> gdStoreimageWrapper = new QueryWrapper<>();
        ((QueryWrapper<GdStoreimage>) gdStoreimageWrapper).eq("storeid", gdStore.getStoreid());
        int del = gdStoreimageMapper.delete(gdStoreimageWrapper);
        if (del > 0) {
            RequestData<List<ManageStoreDTO>> requestData1 = new RequestData<>();
            requestData1.setData(requestData.getData().getManageStoreDTOList());

            manageImageService.inserImagesStore(requestData1);
        }
        return responseData;
    }
}
