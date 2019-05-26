package org.fresh.gd.commons.consts.pojo.dto.shoping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("根据商品ids和门店查询数据模型")
@Data
public class QueryByIdAndStore {
    /**
     * 商品id集合
     */
    @ApiModelProperty("商品id集合")
    private List<Integer> comdityIds;

    /**
     * 门店id
     */
    @ApiModelProperty("门店id")
    private Integer storeid;
}
