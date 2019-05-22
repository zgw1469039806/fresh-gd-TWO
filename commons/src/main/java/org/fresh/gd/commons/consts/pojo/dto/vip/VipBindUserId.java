package org.fresh.gd.commons.consts.pojo.dto.vip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/20 10:47
 * @Description:
 */
@Data
@ApiModel("会员绑定参数DTO")
public class VipBindUserId {

   @NotEmpty
   @ApiModelProperty("会员手机号")
   private String phone;

    @NotEmpty
    @ApiModelProperty("用户编号")
   private Integer userId;

}
