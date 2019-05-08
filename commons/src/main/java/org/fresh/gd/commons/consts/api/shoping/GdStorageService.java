package org.fresh.gd.commons.consts.api.shoping;

import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdStorageDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 入库接口
 *
 * @DATA 2019/4/29 15:05
 * @Author 郭家恒
 * @Description TODO
 */
@RequestMapping("/GdStorageService")
public interface GdStorageService {
    /**
     * 功能描述:
     * 入库
     *
     * @param: [requestData]
     * @return: javax.xml.ws.Response<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/4/29 15:08
     */
    @PostMapping("/saveStora")
    ResponseData<Integer> saveStora(RequestData<GdStorageDTO> requestData);

    /**
     * 功能描述:
     * 查询入库信息
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List   <   org.fresh.gd.commons.consts.pojo.dto.shoping.GdStorageDTO>>
     * @auther: 郭家恒
     * @date: 2019/5/7 18:29
     */
    @PostMapping("/QueryStora")
    ResponseData<List<GdStorageDTO>> QueryStora(RequestData<GdStorageDTO> requestData);
}
