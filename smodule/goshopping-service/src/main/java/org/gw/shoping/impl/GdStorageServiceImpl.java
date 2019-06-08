package org.gw.shoping.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.fresh.gd.commons.consts.api.shoping.GdStorageService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdPurchaseDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdStorageDTO;
import org.fresh.gd.commons.consts.utils.DateUtils;
import org.gw.shoping.fegin.ManageFeginService;
import org.gw.shoping.mapper.GdComdityparticularMapper;
import org.gw.shoping.mapper.GdReplenishMapper;
import org.gw.shoping.mapper.GdStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @DATA 2019/4/29 15:09
 * @Author 郭家恒
 * @Description TODO
 */
@RestController
public class GdStorageServiceImpl implements GdStorageService {
    @Autowired
    private GdStorageMapper gdStorageMapper;

    @Autowired
    private GdReplenishMapper gdCommodityMapper;

    @Autowired
    private GdComdityparticularMapper gdComdityparticularMapper;

    @Autowired
    ManageFeginService manageFeginService;

    /**
     * 功能描述:
     * 入库
     *
     * @param requestData
     * @param: [requestData]
     * @return: javax.xml.ws.Response<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/4/29 15:08
     */
    @Override
    @Transactional
    public ResponseData<Integer> saveStora(@RequestBody RequestData<GdStorageDTO> requestData) {
        ResponseData<Integer> responseData = new ResponseData<>();
        requestData.getData().setStorageTime(DateUtils.getDateFormatFullDe());
        System.out.println(requestData.getData().getStorageTime());
        //添加一条入库记录
        int save = gdStorageMapper.saveStorage(requestData.getData());
        if (save > 0) {
            //如果添加成功修改订单状态
            save = gdCommodityMapper.updPurById(requestData.getData().getReceiptNo());
        }
        List<GdPurchaseDTO> list = gdCommodityMapper.QueryPurByreId(requestData.getData().getReplenishId());
        //添加库存
        for (GdPurchaseDTO dto : list) {
            gdComdityparticularMapper.reduceStock(dto.getShopId(), dto.getShopNumber(), 1);
        }
        responseData.setData(save);
        return responseData;
    }

    /**
     * 功能描述:
     * 查询入库信息
     *
     * @param requestData
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List                               <                               org.fresh.gd.commons.consts.pojo.dto.shoping.GdStorageDTO>>
     * @auther: 郭家恒
     * @date: 2019/5/7 18:29
     */
    @Override
    public ResponseData<List<GdStorageDTO>> QueryStora(@RequestBody RequestData<GdStorageDTO> requestData) {
        ResponseData<List<GdStorageDTO>> responseData = new ResponseData<>();
        List<GdStorageDTO> list = gdStorageMapper.QueryStock(requestData.getData());
        List<Integer> mdIds = new ArrayList<>();
        RequestData<List<Integer>> requestData1 = new RequestData<>();
        for (GdStorageDTO dto:list){
            mdIds.add(dto.getStoreid());
        }
        requestData1.setData(mdIds);
        ResponseData<List<GdStoreDTO>> res = manageFeginService.QueryByid(requestData1);
        for (GdStorageDTO gdStorageDTO : list) {
            for (GdStoreDTO gdStoreDTO : res.getData()) {
                if (gdStorageDTO.getStoreid() == gdStoreDTO.getStoreid()) {
                    gdStorageDTO.setStorename(gdStoreDTO.getStorename());
                }
            }
        }
        responseData.setData(list);
        return responseData;
    }
}
