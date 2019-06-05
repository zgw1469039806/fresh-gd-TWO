package org.gw.shoping.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.fresh.gd.commons.consts.api.shoping.GdCommodityService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.*;
import org.gw.shoping.entity.GdComdityparticular;
import org.gw.shoping.entity.GdCommodity;
import org.gw.shoping.fegin.ManageFeginService;
import org.gw.shoping.mapper.GdComdityparticularMapper;
import org.gw.shoping.mapper.GdCommodityMapper;
import org.gw.shoping.mapper.GdImagesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @DATA 2019-04-21 11:26
 * @Author 张国伟  WeChat:17630376104
 * @Description 商品实现
 */
@RestController
public class GdCommodityServiceImpl implements GdCommodityService {

    @Autowired
    GdCommodityMapper gdCommodityMapper;

    @Autowired
    GdComdityparticularMapper gdComdityparticularMapper;

    @Autowired
    GdImagesMapper gdImagesMapper;
    @Autowired
    ManageFeginService manageFeginService;


    /**
     * 功能描述
     * 查询所有商品
     *
     * @author zgw
     */
    @Override
    public ResponseData<List<GdCommodityDTO>> selShopingAll() {
        ResponseData<List<GdCommodityDTO>> responseData = new ResponseData<>();

        List<GdCommodityDTO> gdCommodityDTOS = gdCommodityMapper.selShopAllUser();

        responseData.setData(gdCommodityDTOS);

        return responseData;
    }

    /**
     * 功能描述
     * 查询所有商品
     *
     * @author zgw
     */
    @Override
    public ResponseData<Page<GdCommodityDTO>> selPageShop(@RequestBody RequestData<GdCommodityDTO> gdCommodityDTORequestData) {
        RequestData<List<GdCommodityDTO>> listRequestData = new RequestData<>();

        ResponseData<Page<GdCommodityDTO>> responseData = new ResponseData<>();
        GdCommodityDTO gdCommodityDTO = gdCommodityDTORequestData.getData();
        Page<GdCommodityDTO> page = new Page<>(gdCommodityDTO.getPageNo(), 6);
        List<GdCommodityDTO> gdCommodityDTOS = gdCommodityMapper.selShopAllAdmin(page, gdCommodityDTO.getComdityname(), gdCommodityDTO.getStoreid(), gdCommodityDTO.getComditytypeId());
        if (gdCommodityDTOS == null) {
            return responseData;
        }
        listRequestData.setData(gdCommodityDTOS);
        ResponseData<List<GdStoreDTO>> listResponseData = manageFeginService.selByssmd(listRequestData);
        for (GdCommodityDTO commodity : gdCommodityDTOS) {
            for (GdStoreDTO store : listResponseData.getData()) {
                if (commodity.getStoreid().equals(store.getStoreid())) {
                    commodity.setSsmdName(store.getStorename());
                    break;
                }
            }
        }
        page.setRecords(gdCommodityDTOS);
        responseData.setData(page);
        return responseData;
    }

    /** 功能描述:
    *
    * @param: []
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO>>
    * @auther: 贾轶飞
    * @date: 2019/5/23 10:29
    */
    @Override
    public ResponseData<List<GdCommodityListDTO>> selheadlineAll() {
        ResponseData<List<GdCommodityListDTO>> responseData = new ResponseData<>();
        responseData.setData(gdCommodityMapper.selheadlineAll());

        return responseData;
    }

    /** 功能描述:
    *商品id查询
    * @param: [comdityId]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityListDTO>
    * @auther: 贾轶飞
    * @date: 2019/5/23 10:29
    */
    @Override
    public ResponseData<GdCommodityListDTO> selOne(@RequestBody Integer comdityId) {
        ResponseData<GdCommodityListDTO> responseData = new ResponseData<>();
        GdCommodityListDTO dto = gdCommodityMapper.selOne(comdityId);
        //图片信息
        GdImagesDTO dto2=  gdImagesMapper.queryimages(comdityId);
        dto2.setImagesDTOS(gdImagesMapper.queryimagesTwo(comdityId));
        dto.setGdImagesDTO(dto2);
        responseData.setData(dto);
        return responseData;
    }
    @Override
    public ResponseData<GdCommodityListDTO> selOnes(@RequestBody RequestData<GoodsDetailQueryDTO> requestData) {
        ResponseData<GdCommodityListDTO> responseData = new ResponseData<>();
        //查询信息
        GdCommodityListDTO dto = gdCommodityMapper.selOnes(requestData.getData());
        //图片信息
        GdImagesDTO dto2=  gdImagesMapper.queryimages(requestData.getData().getComdityId());
        dto2.setImagesDTOS(gdImagesMapper.queryimagesTwo(requestData.getData().getComdityId()));
        dto.setGdImagesDTO(dto2);
        responseData.setData(dto);
        return responseData;
    }
    /**
     * 功能描述
     * 货品详情信息
     *
     * @param requestData
     * @author zgw
     */
    @Override
    public ResponseData<List<GdinventoryallDTO>> nventoryall(@RequestBody RequestData<GdComditynameDTO> requestData) {
        RequestData<List<GdCommodityDTO>> listRequestData = new RequestData<>();
        List<GdCommodityDTO> list = new ArrayList<>();
        ResponseData<List<GdinventoryallDTO>> responseData = new ResponseData<>();
        GdComditynameDTO gdComditynameDTO = requestData.getData();

        List<GdinventoryallDTO> nventoryallmap = gdCommodityMapper.nventoryallmap(gdComditynameDTO);
        for (GdinventoryallDTO dto : nventoryallmap) {
            GdCommodityDTO commodityDTO = new GdCommodityDTO();
            commodityDTO.setStoreid(dto.getStoreid());
            list.add(commodityDTO);
        }
        listRequestData.setData(list);
        ResponseData<List<GdStoreDTO>> listResponseData = manageFeginService.selByssmd(listRequestData);
        for (GdinventoryallDTO commodity : nventoryallmap) {
            for (GdStoreDTO store : listResponseData.getData()) {
                if (commodity.getStoreid().equals(store.getStoreid())) {
                    commodity.setStoreName(store.getStorename());
                    break;
                }
            }
        }
        responseData.setData(nventoryallmap);

        return responseData;
    }

    @Override
    public ResponseData<List<GdCommodityDTO>> QueryComByType(@RequestBody RequestData<Integer> requestData) {
        ResponseData<List<GdCommodityDTO>> responseData = new ResponseData<>();
        List<GdCommodityDTO> list = gdCommodityMapper.QueryComByType(requestData.getData());
        responseData.setData(list);
        return responseData;
    }

    /**
     * 功能描述:
     * 查询所有商品根据条件
     *
     * @param queryData
     * @param: [queryData]
     * @auther: 郭家恒
     * @date: 2019/5/14 18:13
     */
    @Override
    public ResponseData<List<GdCommodityDTO>> QueryShopbyWh(@RequestBody RequestData<ComdityQueryDTO> queryData) {
        List<GdCommodityDTO> list = gdCommodityMapper.QueryShop(queryData.getData());
        List<Integer> storeids = new ArrayList<>();
        if (list.size() > 0) {
            for (GdCommodityDTO dto : list) {
                storeids.add(dto.getStoreid());
            }
            RequestData<List<Integer>> requestData = new RequestData<>();
            requestData.setData(storeids);
            ResponseData<List<GdStoreDTO>> responseData = manageFeginService.QueryByid(requestData);
            for (GdCommodityDTO cdto : list) {
                for (GdStoreDTO sdto : responseData.getData()) {
                    if (cdto.getStoreid() == sdto.getStoreid()) {
                        cdto.setSsmdName(sdto.getStorename());
                        break;
                    }
                }
            }
        }
        ResponseData<List<GdCommodityDTO>> responseData1 = new ResponseData<>();
        responseData1.setData(list);
        return responseData1;
    }

    /**
     * 功能描述:
     * 删除商品
     *
     * @param requestData
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/14 22:17
     */
    @Transactional
    @Override
    public ResponseData<Integer> delShop(@RequestBody RequestData<GdCommodityDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        GdCommodity gdCommodity = new GdCommodity();
        BeanUtils.copyProperties(requestData.getData(), gdCommodity);
        GdComdityparticular gdComdityparticular = new GdComdityparticular();
        BeanUtils.copyProperties(requestData.getData(), gdComdityparticular);
        Wrapper<GdCommodity> wrapper = new QueryWrapper<>();
        Wrapper<GdComdityparticular> pawrapper = new QueryWrapper<>();
        ((QueryWrapper<GdComdityparticular>) pawrapper).eq("comdityId", gdComdityparticular.getComdityId());
        ((QueryWrapper<GdComdityparticular>) pawrapper).in("storeid", requestData.getData().getStoreidlist());
        int del = gdComdityparticularMapper.delete(pawrapper);
        if (del > 0) {
            pawrapper = new QueryWrapper<>();
            ((QueryWrapper<GdComdityparticular>) pawrapper).eq("comdityId", gdCommodity.getComdityId());
            List<GdComdityparticular> list = gdComdityparticularMapper.selectList(pawrapper);
            if (list.size() > 0) {
                return responseData;
            } else {
                ((QueryWrapper<GdCommodity>) wrapper).eq("comdityId", gdCommodity.getComdityId());
                gdCommodityMapper.delete(wrapper);
            }
        }
        return responseData;
    }

    /**
     * 功能描述:
     * 添加商品
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/14 20:43
     */
    @Transactional
    @Override
    public ResponseData<Integer> addShop(@RequestBody RequestData<GdCommodityDTO> requestData) {
        GdCommodity gdCommodity = new GdCommodity();
        BeanUtils.copyProperties(requestData.getData(), gdCommodity);
        GdComdityparticular gdComdityparticular = new GdComdityparticular();
        BeanUtils.copyProperties(requestData.getData(), gdComdityparticular);
        //添加商品
        gdCommodityMapper.insert(gdCommodity);
        for (int storeid : requestData.getData().getStoreidlist()) {
            gdComdityparticular.setStock(0);
            gdComdityparticular.setComdityId(gdCommodity.getComdityId());
            gdComdityparticular.setStoreid(storeid);
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.substring(4);
            String tiaoxing = "69" + uuid;
            System.out.println(tiaoxing);
            gdComdityparticular.setComdityBM(tiaoxing);
            //todo: 吧商品详细的添加合并为一条sql语句.商品图片的添加
            //添加商品详细
            gdComdityparticularMapper.insert(gdComdityparticular);
        }
        GdImagesDTO gdImagesDTO = requestData.getData().getGdImagesDTO();
        gdImagesDTO.setComdityId(gdCommodity.getComdityId());
        gdImagesDTO.setImageslv("0");
        //商品图片添加开始
        GdImages gdImages = new GdImages();
        BeanUtils.copyProperties(gdImagesDTO, gdImages);
        gdImages.setComdityId(gdCommodity.getComdityId());
        gdImages.setImageslv("1");
        List<GdImages> list = new ArrayList<>();
        list.add(gdImages);
        for (GdImages image : gdImagesDTO.getImagesDTOS()) {
            image.setComdityId(gdCommodity.getComdityId());
            image.setImageslv("2");
            list.add(image);
        }
        gdImagesMapper.saveImages(list);
        ResponseData<Integer> responseData = new ResponseData<>();
        return responseData;
    }

    /**
     * 功能描述:
     * 修改商品
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/14 20:48
     */
    @Transactional
    @Override
    public ResponseData<Integer> updShop(@RequestBody RequestData<GdCommodityDTO> requestData) {
        GdCommodity gdCommodity = new GdCommodity();
        BeanUtils.copyProperties(requestData.getData(), gdCommodity);
        GdComdityparticular gdComdityparticular = new GdComdityparticular();
        BeanUtils.copyProperties(requestData.getData(), gdComdityparticular);
        Wrapper<GdCommodity> wrapper = new QueryWrapper<>();
        ((QueryWrapper<GdCommodity>) wrapper).eq("comdityId", gdCommodity.getComdityId());
        int upd = gdCommodityMapper.update(gdCommodity, wrapper);
        //如果商品表修改成功
        if (upd > 0) {
            Wrapper<GdComdityparticular> wrapper1 = new QueryWrapper<>();
            ((QueryWrapper<GdComdityparticular>) wrapper1).eq("comdityId", gdComdityparticular.getComdityId());
            //根据集合id批量修改商品详细
            ((QueryWrapper<GdComdityparticular>) wrapper1).in("storeid", requestData.getData().getStoreidlist());
            upd = gdComdityparticularMapper.update(gdComdityparticular, wrapper1);
        }
        ResponseData<Integer> responseData = new ResponseData<>();
        return responseData;
    }

    /**
     * 功能描述:
     * 同步商品数据至目标门店
     *
     * @param requestData
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/14 23:18
     */
    @Override
    public ResponseData<Integer> synchronizationShop(@RequestBody RequestData<synchronizationDTO> requestData) {
        List<GdComdityparticularDTO> list = requestData.getData().getList();
        int storeid = requestData.getData().getStoreid();
        for (GdComdityparticularDTO dto : list) {
            dto.setStoreid(storeid);
        }
        int save = gdComdityparticularMapper.addPar(list);
        ResponseData<Integer> responseData = new ResponseData<>();
        responseData.setData(save);
        return responseData;
    }

    /**
     * 功能描述:
     * 查询可同步至目标门店的商品
     *
     * @param requestData
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/14 23:23
     */
    @Override
    public ResponseData<List<GdCommodityDTO>> QuerySync(@RequestBody RequestData<Integer> requestData) {
        List<GdCommodityDTO> list = gdCommodityMapper.QuerySync(requestData.getData());
        ResponseData<List<GdCommodityDTO>> responseData = new ResponseData<>();
        responseData.setData(list);
        return responseData;
    }

    /**
     * 功能描述:
     * 根据商品id集合查询商品与商品详情
     * (商品id,商品名称，商品规格)
     *
     * @param requestData
     * @param: [requestData]
     * @auther: 郭家恒
     * @date: 2019/5/15 9:49
     */
    @Override
    public ResponseData<List<GdCommodityDTO>> QueryShopByIds(@RequestBody List<Integer> requestData) {
        ResponseData<List<GdCommodityDTO>> responseData = new ResponseData<>();
        List<GdCommodityDTO> list = gdCommodityMapper.QueryShopByIds(requestData);
        responseData.setData(list);
        return responseData;
    }


    @Override
    public ResponseData<List<GdCommodityDTO>> QueryShopByIdsTwo(@RequestBody List<Integer> requestData) {
        ResponseData<List<GdCommodityDTO>> responseData = new ResponseData<>();
        List<GdCommodityDTO> list = gdCommodityMapper.QueryShopByIdsTwo(requestData);
        responseData.setData(list);
        return responseData;
    }


    /**
     * 功能描述
     * 商品上下架操作
     *
     * @param requestData
     * @return org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @author zgw
     */
    @Override
    public ResponseData<Integer> StandandDown(@RequestBody RequestData<GdcomdityHhDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();

        GdcomdityHhDTO gdcomdityHhDTO = requestData.getData();

        Integer standandDown = gdComdityparticularMapper.StandandDown(gdcomdityHhDTO);
        if (standandDown > 0) {
            responseData.setMsg("修改状态成功");
            return responseData;
        }
        responseData.setMsg("修改状态失败");

        return responseData;
    }
}
