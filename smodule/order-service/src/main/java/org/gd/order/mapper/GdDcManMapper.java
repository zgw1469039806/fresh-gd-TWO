package org.gd.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fresh.gd.commons.consts.pojo.dto.order.GdDcManDTO;
import org.gd.order.entity.GdDcMan;
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
public interface GdDcManMapper extends BaseMapper<GdDcMan> {

    public Integer logind(@Param("loginpwd")String loginpwd,@Param("phone")String phone);
}
