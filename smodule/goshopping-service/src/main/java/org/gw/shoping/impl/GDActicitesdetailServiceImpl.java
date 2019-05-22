package org.gw.shoping.impl;

import org.fresh.gd.commons.consts.api.wx.comdity.GDActicitesdetailService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesDTO;
import org.gw.shoping.mapper.GdActivitiesMapper;
import org.gw.shoping.mapper.GdImagesMapper;
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

    /**
     * 功能描述:
     * 查询带活动的商品
     *
     * @param: []
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List   <   org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO>>
     * @auther: 贾轶飞
     * @date: 2019/5/15 15:26
     */
    @Override
    public ResponseData<List<GdActivitiesAndShopDTO>> queryActivitiesGoods(@RequestBody RequestData<GdActivitiesDTO>
            requestData) {
        List<GdActivitiesAndShopDTO> list2=new ArrayList<>();
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

    @Override
    public ResponseData<List<GdActivitiesDTO>> queryActivities() {
        ResponseData<List<GdActivitiesDTO>> responseData=new ResponseData<>();

        responseData.setData(gdActivitiesMapper.queryActivities());
        return responseData;
    }
}
