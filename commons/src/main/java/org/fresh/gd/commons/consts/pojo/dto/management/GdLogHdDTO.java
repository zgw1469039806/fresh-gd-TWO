package org.fresh.gd.commons.consts.pojo.dto.management;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @DATA 2019-05-08 15:04
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */

@ApiModel("操作日志返回信息")
@Data
public class GdLogHdDTO
{
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("操作")
    private String operation;

    @ApiModelProperty("方法名")
    private String method;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("操作时间")
    private String createDate;

    @ApiModelProperty("店铺名称")
    private String storename;


}
