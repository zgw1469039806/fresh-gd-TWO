package org.gw.shoping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.shoping.GdStorageDTO;
import org.gw.shoping.entity.GdStorage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 入库表 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Mapper
public interface GdStorageMapper extends BaseMapper<GdStorage> {

    /**
     * 功能描述:
     * 添加一条入库
     *
     * @param: [gdStoreDTO]
     * @return: java.lang.Integer
     * @auther: 郭家恒
     * @date: 2019/4/29 15:10
     */
    Integer saveStorage(GdStorageDTO gdStoreDTO);
    /**
     * 功能描述:
     * 根据进货编号删除入库信息
     *
     * @param: [rid]
     * @return: java.lang.Integer
     * @auther: 郭家恒
     * @date: 2019/5/6 16:15
     */
    Integer delStorageByRid(@Param("rid") Integer rid);

    /** 功能描述:
    * 查询符合条件的所有入库记录
    * @param: []
    * @return: java.util.List<org.gw.shoping.entity.GdStorage>
    * @auther: 郭家恒
    * @date: 2019/5/8 14:10
    */
    List<GdStorageDTO> QueryStock(GdStorageDTO gdStorageDTO);
}
