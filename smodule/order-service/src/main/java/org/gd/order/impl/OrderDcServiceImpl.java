package org.gd.order.impl;

import org.fresh.gd.commons.consts.api.order.GDdcService;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcManDTO;
import org.gd.order.mapper.GdDcManMapper;
import org.gd.order.mapper.GdDcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 贾轶飞
 * @date 2019/6/9 6:24
 */
@RestController
public class OrderDcServiceImpl implements GDdcService {
    @Autowired
    private GdDcManMapper gdDcManMapper;

    @Autowired
    private GdDcMapper gdDcMapper;

    @Override
    public ResponseData<Integer> login(@RequestBody RequestData<GdDcManDTO> requestData) {

        ResponseData<Integer> responseData = new ResponseData<>();

        Integer i = gdDcManMapper.logind(requestData.getData().getPhone(), requestData.getData().getLoginpwd());
        System.out.println(i);
        if (i > 0) {
            responseData.setData(i);
            return responseData;
        }else {
            responseData.setMsg("登陆失败");
        }


        return responseData;
    }

    @Override
    public ResponseData<GdDcDTO> gddcquery(@RequestBody RequestData<GdDcDTO> requestData) {
        ResponseData<GdDcDTO> responseData = new ResponseData<>();
        GdDcDTO dto = gdDcMapper.queryDc(requestData.getData());
        if (dto.getOrderid() == null) {
            responseData.setMsg("该配送员没有订单");
            return responseData;
        }
        responseData.setData(dto);
        return responseData;
    }
}
