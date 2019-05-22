package org.fresh.gd.commons.consts.api.wx.comdity;

import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 贾轶飞
 * @dat e2019/5/15 14:19
 */
@RequestMapping("/GDActicitesdetailService")
public interface GDActicitesdetailService {

    /** 功能描述:
     * 查询带活动的商品
     * @param: []
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO>>
     * @auther: 贾轶飞
     * @date: 2019/5/15 15:26
     */
    @PostMapping("/queryGoods")
    public ResponseData<List<GdActivitiesAndShopDTO>> queryActivitiesGoods(RequestData<GdActivitiesDTO> requestData);

    /** 功能描述:
    * 查询线上，以上线的活动
    * @param: []
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List<org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesDTO>>
    * @auther: 贾轶飞
    * @date: 2019/5/15 17:25
    */
    @PostMapping("/queryActivities")
    public ResponseData<List<GdActivitiesDTO>> queryActivities();

}
