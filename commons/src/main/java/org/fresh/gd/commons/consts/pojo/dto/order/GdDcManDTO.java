package org.fresh.gd.commons.consts.pojo.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 贾轶飞
 * @date 2019/6/8 18:05
 */
@Data
@ApiModel("配送员工数据模型")
public class GdDcManDTO {

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
     * 登陆密码
     */
    @ApiModelProperty("登陆密码")
    private String loginpwd;

    /**
     * 工作手机号
     */
    @ApiModelProperty("工作手机号")
    private String phone;


}
