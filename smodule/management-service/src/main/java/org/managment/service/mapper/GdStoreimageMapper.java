package org.managment.service.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.ManageStoreDTO;
import org.managment.service.entity.GdStoreimage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-17
 */
@Mapper
public interface GdStoreimageMapper extends BaseMapper<GdStoreimage> {

    Integer saveImage(List<ManageStoreDTO> gdStoreimage);

    @Delete(" delete from  gd_storeImage  where storeid=#{storeid} ")
    Integer deleteByIdStro(@Param("storeid") Integer storeid);

}
