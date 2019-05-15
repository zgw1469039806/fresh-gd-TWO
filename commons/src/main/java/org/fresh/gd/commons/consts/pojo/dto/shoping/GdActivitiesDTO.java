package org.fresh.gd.commons.consts.pojo.dto.shoping;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 贾轶飞
 * @dat e2019/5/15 9:41
 */
@ApiModel("活动信息")
@Data
public class GdActivitiesDTO {
    /**
     * 活动编号
     */
    @ApiModelProperty("活动编号")
    private Integer activityId;

    /**
     * 活动名称
     */
    @ApiModelProperty("活动名称")
    private String activityname;

    /**
     * 活动描述
     */
    @ApiModelProperty("活动描述")
    private String adescription;

    /**
     * 活动图片
     */
    @ApiModelProperty("活动图片")
    private String aimages;


}
