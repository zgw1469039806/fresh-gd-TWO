package org.fresh.gd.commons.consts.pojo.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统计报表Dto
 *
 * @Date: 11:45 2019/5/24
 * @Author: 郭家恒
 */
@Data
@ApiModel("统计报表数据模型")
public class GdDeportFormDTO {
    /**
     * 年份条件
     */
    @ApiModelProperty("年份条件")
    private String selyear;

    /**
     * 成本
     */
    private float summoney;

    /**
     *  月份
     */
    private String mones;

    /**
     * 年份条件
     */
    @ApiModelProperty("年份条件")
    private String storeid;

//    /**
//     * 一月数据
//     */
//    @ApiModelProperty("一月数据")
//    private String january;
//
//    /**
//     * 二月
//     */
//    @ApiModelProperty("二月")
//    private String february;
//
//    /**
//     * 三月
//     */
//    @ApiModelProperty("三月")
//    private String march;
//
//    /**
//     * 四月
//     */
//    @ApiModelProperty("四月")
//    private String april;
//
//    /**
//     * 五月
//     */
//    @ApiModelProperty("五月")
//    private String may;
//
//    /**
//     * 六月
//     */
//    @ApiModelProperty("六月")
//    private String june;
//
//    /**
//     * 七月
//     */
//    @ApiModelProperty("七月")
//    private String july;
//
//    /**
//     * 八月
//     */
//    @ApiModelProperty("八月")
//    private String agust;
//
//    /**
//     * 九月
//     */
//    @ApiModelProperty("九月")
//    private String september;
//
//
//    /**
//     * 十月
//     */
//    @ApiModelProperty("十月")
//    private String october;
//
//    /**
//     * 十一月
//     */
//    @ApiModelProperty("十一月")
//    private String november;
//
//    /**
//     * 十二月
//     */
//    @ApiModelProperty("十二月")
//    private String december;
}
