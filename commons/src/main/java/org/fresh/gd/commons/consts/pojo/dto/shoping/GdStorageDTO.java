package org.fresh.gd.commons.consts.pojo.dto.shoping;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @DATA 2019/4/29 15:14
 * @Author 郭家恒
 * @Description TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdStorageDTO {
    /**
     * 入库编号
     */
    private Integer storageid;

    /**
     * 进货ID
     */
    private Integer replenishId;

    /**
     * 入库时间
     */
    private String storageTime;

    /**
     * 入库人员ID
     */
    private Integer storageuserid;

    /**
     * 入库人员姓名
     */
    private String userName;

    /**
     * 进货单号
     */
    private String receiptNo;

    /**
     * 开始时间
     */
    private String staictime;

    /**
     * 结束时间
     */
    private String endtime;

    /**
     * 进货时间
     */
    @ApiModelProperty("进货时间")
    private String replenishTime;

    /**
     * 店铺ID
     */
    @ApiModelProperty("店铺ID")
    private Integer storeid;
    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    private String storename;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
}