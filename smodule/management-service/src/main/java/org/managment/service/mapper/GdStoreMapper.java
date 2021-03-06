package org.managment.service.mapper;

import org.apache.ibatis.annotations.*;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.ManageStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.oauth.UserDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdCommodityDTO;
import org.managment.service.entity.GdStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Mapper
public interface GdStoreMapper extends BaseMapper<GdStore> {

    @Insert("INSERT into gd_store(storename,storeaddress,storeaLogo) value(#{storename},#{storeaddress},#{storeaLogo})")
    @Options(useGeneratedKeys=true, keyProperty="storeid", keyColumn="storeid")
    Integer save(GdStore gdStore);

    @Select("select storeid,storename,storeaddress from gd_store where storename=#{storename}")
    ManageStoreDTO selByName(@Param("storename") String storename);


    List<GdStoreDTO> selStoreAndImageByName(@Param("storename") String storename);

    @Delete(" delete from  gd_store  where storeid=#{storeid}")
    Integer deleteByIdStro(@Param("storeid") Integer storeid);

    List<GdStoreDTO> selStoreAndImageByList(List<UserDTO> list);

    List<GdStoreDTO> selSSDPById(List<GdCommodityDTO> list);

    /**
     * 根据门店ID查询
     *
     * @param list
     * @return
     */
    List<GdStoreDTO> QueryById(List<Integer> list);
}
