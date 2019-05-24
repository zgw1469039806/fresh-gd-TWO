package org.gw.shoping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdActivitiesAndShopDTO;
import org.gw.shoping.entity.GdImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


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
    GdActivitiesAndShopDTO queryImage(@Param("comdityid")Integer comdityid);

    /**
     * 添加商品图片链接
     * @Date: 14:04 2019/5/23
     * @Author: 郭家恒
     */
    Integer saveImages(List<org.fresh.gd.commons.consts.pojo.dto.shoping.GdImages> list);
}
