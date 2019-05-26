package org.gw.shoping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitesdetailDTO;

/**
 * <p>
 * 
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GdActivities implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @TableId(value = "activityId", type = IdType.AUTO)
    private Integer activityId;

    /**
     * 活动名称
     */
    private String activityname;

    /**
     * 活动描述
     */
    private String adescription;

    /**
     * 活动图片
     */
    private String aimages;

    /**
     * 活动详细
     */
    private List<GdActivitesdetail> list;

    //门店id
    private Integer storeid;
}
