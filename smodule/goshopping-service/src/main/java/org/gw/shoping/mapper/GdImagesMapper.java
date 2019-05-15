package org.gw.shoping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO;
import org.gw.shoping.entity.GdImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Mapper
public interface GdImagesMapper extends BaseMapper<GdImages> {

    @Select("select * from gd_images where comdityid=#{comdityid} limit 1")
    public GdActivitiesAndShopDTO queryImage(@Param("comdityid")Integer comdityid);
}
