package org.fresh.gd.unification.controller.management;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.utils.OSSClientUtil;
import org.fresh.gd.unification.fegin.management.ManaFeginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @DATA 2019-04-17 15:15
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@Api(description = "添加门店信息")
@Slf4j
@RestController
@RequestMapping("/unification")
public class ManaController {
    @Autowired
    ManaFeginService manaFeginService;

    @PostMapping("/imageAdd")
    public ResponseData<String> imageAdd(MultipartHttpServletRequest request) {
        MultipartFile multipartFile = request.getFile("file");
        OSSClientUtil ossClient = new OSSClientUtil();
        ResponseData<String> responseData = new ResponseData<>();
        try {
            if (multipartFile == null || multipartFile.getSize() <= 0) {
                log.info("图片不能为空");
            }
            String name = ossClient.uploadImg2Oss(multipartFile);
            String imgUrl = ossClient.getImgUrl(name);
            responseData.setData(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    @PostMapping("/selStorAndImage")
    public ResponseData<List<GdStoreDTO>> selStorAndImage(@RequestBody GdStoreDTO manageStoreDTO) {
        ResponseData<List<GdStoreDTO>> listResponseData = manaFeginService.selStroreByName(manageStoreDTO);
        List<GdStoreDTO> data = listResponseData.getData();
        return listResponseData;
    }

    /**
     * 功能描述:
     * 查询所有门店
     *
     * @param: []
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.util.List               <               org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO>>
     * @auther: zgw
     * @date: 2019/5/10 13:52
     */
    @PostMapping("/GdStoreQueryAll")
    public ResponseData<List<GdStoreDTO>> QueryAll(@RequestBody RequestData<String> requestData) {
        return manaFeginService.QueryAll(requestData);
    }

    /**
     * 功能描述:
     * 保存门店
     *
     * @param: [gdStoreDTO]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/9 18:40
     */
    @PostMapping("/saveMana")
    public ResponseData<Integer> saveMana(@RequestBody GdStoreDTO gdStoreDTO) {
        return manaFeginService.inserStore(gdStoreDTO);
    }

    /**
     * 功能描述:
     * 修改门店信息
     *
     * @param: [requestData]
     * @return: org.fresh.gd.commons.consts.pojo.ResponseData<java.lang.Integer>
     * @auther: 郭家恒
     * @date: 2019/5/10 14:10
     */
    @PostMapping("/updMana")
    public ResponseData<Integer> updMana(@RequestBody RequestData<GdStoreDTO> requestData) {
         return manaFeginService.updManna(requestData);
    }
}
