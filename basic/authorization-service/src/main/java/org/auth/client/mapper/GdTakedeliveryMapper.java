package org.auth.client.mapper;

import org.apache.ibatis.annotations.*;
import org.auth.client.entity.GdTakedelivery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.fresh.gd.commons.consts.pojo.dto.user.UserAddressDTO;

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

    @Select("select * from gd_takedelivery t ,gd_user u where t.userid =u.userid and t.userid =#{userid}")
    public List<UserAddressDTO> queryAddress(@Param("userid") Integer userid);

    @Insert("insert into gd_takedelivery(`userid`,`address`,`UPDATED_BY`,`UPDATED_TIME`,`phone`,`consignee`,`status`) values (#{userid},#{address},#{updatedBy},#{updatedTime},#{phone}," +
            "#{consignee},#{status})")
    public Integer addAddress(GdTakedelivery gdTakedelivery);

    @Delete("delete from gd_takedelivery where takedeliveryidid=#{takedeliveryidid} and userid =#{userid}")
    public Integer delAddress(@Param("takedeliveryidid")Integer takedeliveryidid,@Param("userid")Integer userid);

    @Select("select * from gd_takedelivery where userid =#{userid} and status =#{status}")
    public GdTakedelivery queryOne(@Param("userid")Integer userid,@Param("status")Integer status);

    public Integer updAddress(GdTakedelivery gdTakedelivery);

    public Integer queryCount(GdTakedelivery GdTakedelivery);
}
