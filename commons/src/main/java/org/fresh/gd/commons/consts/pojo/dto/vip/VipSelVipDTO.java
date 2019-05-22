package org.fresh.gd.commons.consts.pojo.dto.vip;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/22 10:24
 * @Description:
 */

@Data
@ApiModel("根据手机号或用户id查询会员信息DTO")
public class VipSelVipDTO {

    @ApiModelProperty("会员手机号")
    private String vipPhone;

    @ApiModelProperty("用户编号")
    private Integer userId;
}
