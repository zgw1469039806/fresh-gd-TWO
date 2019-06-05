package org.gw.shoping.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.fresh.gd.commons.consts.api.wx.comdity.GDActicitesdetailService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.shoping.ComdityQueryDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.gw.shoping.entity.GdActivitesdetail;
import org.gw.shoping.entity.GdActivities;
import org.gw.shoping.entity.GdComdityparticular;
import org.gw.shoping.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/15 14:16
 */
@RestController
public class GDActicitesdetailServiceImpl implements GDActicitesdetailService {


    @Autowired
    private GdActivitiesMapper gdActivitiesMapper;

    @Autowired
    private GdImagesMapper gdImagesMapper;

    @Autowired
    private GdActivitesdetailMapper gdActivitesdetailMapper;

    @Autowired
    private GdCommodityMapper gdCommodityMapper;

    /**
     * 功能描述:
     * 查询带活动的商品
     *
     * @param: []
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List                                                                                                                               <                                                                                                                               org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO>>
     * @auther: 贾轶飞
     * @date: 2019/5/15 15:26
     */
    @Override
    public ResponseData<List<GdActivitiesAndShopDTO>> queryActivitiesGoods(@RequestBody RequestData<GdActivitiesDTO>
                                                                                   requestData) {
        List<GdActivitiesAndShopDTO> list2 = new ArrayList<>();
        ResponseData<List<GdActivitiesAndShopDTO>> responseData = new ResponseData<>();
        List<GdActivitiesAndShopDTO> list = gdActivitiesMapper.queryActivitiesGoods(requestData.getData());
        for (GdActivitiesAndShopDTO dto : list
                ) {
            GdActivitiesAndShopDTO dto2 = gdImagesMapper.queryImage(dto.getComdityId());
            dto.setImagesurl(dto2.getImagesurl());
            list2.add(dto);
        }


        responseData.setData(list2);
        return responseData;
    }

    /**
     * 功能描述:
     * 查询活动
     *
     * @param: []
     * @auther: 贾轶飞
     * @date: 2019/5/26 11:28
     */
    @Override
    public ResponseData<Object> queryActivities() {
        ResponseData<Object> responseData = new ResponseData<>();
        List<GdActivities> gdActivitiesDTOS = gdActivitiesMapper.queryActivities();
        for (GdActivities activitiesDTO : gdActivitiesDTOS) {
            Wrapper<GdActivitesdetail> gdActivitesdetailWrapper = new QueryWrapper<>();
            ((QueryWrapper<GdActivitesdetail>) gdActivitesdetailWrapper).eq("activites", activitiesDTO.getActivityId());
            List<GdActivitesdetail> list = gdActivitesdetailMapper.selectList(gdActivitesdetailWrapper);
            activitiesDTO.setList(list);
            for (GdActivitesdetail detail : activitiesDTO.getList()) {
                ComdityQueryDTO comdityQueryDTO = new ComdityQueryDTO();
                comdityQueryDTO.setComdityId(detail.getCommodityId().toString());
                comdityQueryDTO.setStoreid(activitiesDTO.getStoreid());
                List<GdCommodityDTO> commodityDTOList = gdCommodityMapper.QueryShop(comdityQueryDTO);
                if (commodityDTOList.size() > 0) {
                    BeanUtils.copyProperties(commodityDTOList.get(0), detail);
                }
            }
        }
        responseData.setData(gdActivitiesDTOS);
        return responseData;
    }
}
