package org.fresh.gd.commons.consts.api.order;

import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcManDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 贾轶飞
 * @date 2019/6/8 18:54
 */
@RequestMapping("/GDdcService")
public interface GDdcService {
    /** 功能描述:
    * 骑手登陆
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.order.GdDcManDTO>
    * @auther: 贾轶飞
    * @date: 2019/6/9 6:22
    */
    @PostMapping("/gddclogin")
    public ResponseData<Integer> login(RequestData<GdDcManDTO> requestData);

    /** 功能描述:
    * 骑手任务订单查询
    * @param: [requestData]
    * @return: org.fresh.gd.commons.consts.pojo.ResponseData<org.fresh.gd.commons.consts.pojo.dto.order.GdDcDTO>
    * @auther: 贾轶飞
    * @date: 2019/6/9 6:23
    */
    @PostMapping("/gddcquery")
    public ResponseData<GdDcDTO> gddcquery(RequestData<GdDcDTO> requestData);


}
