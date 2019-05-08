package org.fresh.gd.commons.consts.pojo.dto.management;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @DATA 2019-05-08 14:13
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@ApiModel("操作日志")
@Data
public class GdLogDTO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("开始时间")
    private String StartTime;

    @ApiModelProperty("结束时间")
    private String EndTime;


}
