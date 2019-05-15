package org.fresh.gd.commons.consts.pojo.dto.shoping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @DATA 2019-05-15 14:19
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@ApiModel("上下架参数")
@Data
public class GdcomdityHhDTO {


    @ApiModelProperty("商品状态0-下架1-上架")
    private Integer comstateId;

    @ApiModelProperty("商品ID")
    private Integer comdityId;

    @ApiModelProperty("门店ID")
    private Integer storeId;
}
