package org.fresh.gd.commons.consts.pojo.dto.shoping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 贾轶飞
 * @dat e2019/5/26 21:31
 */
@ApiModel("商品详细查询数据模型")
@Data
public class GoodsDetailQueryDTO {


    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Integer comdityId;


    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    private Integer storeid;
}
