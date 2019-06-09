package org.gd.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcManDTO;
import org.gd.order.entity.GdDc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-06-08
 */
@Mapper
public interface GdDcMapper extends BaseMapper<GdDc> {


    public GdDcDTO queryDc(GdDcDTO dto);
}