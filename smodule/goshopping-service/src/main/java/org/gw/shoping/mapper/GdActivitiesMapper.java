package org.gw.shoping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesDTO;
import org.gw.shoping.entity.GdActivities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-29
 */
@Mapper
public interface GdActivitiesMapper extends BaseMapper<GdActivities> {


   public List<GdActivitiesAndShopDTO> queryActivitiesGoods();

   /** 功能描述:
   * 查询所有上线活动
   * @param: []
   * @return: java.util.List<org.gw.shoping.entity.GdActivities>
   * @auther: 贾轶飞
   * @date: 2019/5/15 17:22
   */
   @Select("select * from gd_activities")
   public List<GdActivitiesDTO> queryActivities();
}
