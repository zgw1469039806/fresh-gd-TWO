package org.auth.client.mapper;

import org.apache.ibatis.annotations.*;
import org.auth.client.entity.GdTakedelivery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 收货地址详细 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-11
 */
@Mapper
public interface GdTakedeliveryMapper extends BaseMapper<GdTakedelivery> {

    @Select("select * from gd_takedelivery where userid =#{userid}")
    public List<GdTakedelivery> queryAddress(@Param("userid") Integer userid);

    @Insert("inster into gd_takedelivery values (null,#{userid},#{address},#{UPDATED_BY},#{UPDATED_TIME})")
    public Integer addAddress(GdTakedelivery gdTakedelivery);

    @Delete("delete from gd_takedelivery where takedeliveryidid=#{takedeliveryidid}")
    public Integer delAddress(@Param("takedeliveryidid")Integer takedeliveryidid);

    @Update("update gd_takedelivery set address=#{address},UPDATED_BY=#{updatedBy},UPDATED_TIME=#{updatedTime} where " +
            "takedeliveryidid =#{takedeliveryidid}")
    public Integer updAddress(GdTakedelivery gdTakedelivery);
}
