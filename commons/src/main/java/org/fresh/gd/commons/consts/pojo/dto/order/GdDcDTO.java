package org.fresh.gd.commons.consts.pojo.dto.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 贾轶飞
 * @date 2019/6/8 18:19
 */
@Data
@ApiModel("配送详细数据模型")
public class GdDcDTO {
    /**
     * 配送详情id
     */
    @ApiModelProperty("配送详情id")
    private Integer did;

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private String orderid;


    /**
     * 1.差 2.一般 3.良好 4.优秀
     */
    @ApiModelProperty("评价")
    private Integer score;

    /**
     * 配送员ID
     */
    @ApiModelProperty("配送员ID")
    private Integer pid;
    /**
     * 配送人姓名
     */
    @ApiModelProperty("配送人姓名")
    private String pname;


    /**
     * 配送所消耗的时间
     */
    @ApiModelProperty("配送所消耗的时间")
    private String dctime;
}
