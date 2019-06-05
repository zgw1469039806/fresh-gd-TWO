package org.gd.order.mapper;

import org.apache.ibatis.annotations.*;
import org.gd.order.entity.GdShoppingcart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Mapper
public interface GdShoppingcartMapper extends BaseMapper<GdShoppingcart> {

    @Select("select * from gd_shoppingcart where userid=#{userid}")
    GdShoppingcart selGwcByUserId(@Param("userid") String userid);

    @Select("select * from gd_shoppingcart where userid=#{userid} and comdityId =#{comdityId}")
    GdShoppingcart queryOne(@Param("userid")Integer userid, @Param("comdityId")Integer comdityId);
    /**
     * 功能描述:
     * 根据用户查询当前用户购物车信息
     *
     * @param: [useraccount]用户账号
     * @return: java.util.List<org.gd.order.entity.GdShoppingcart>
     * @auther: 贾轶飞
     * @date: 2019/5/6 10:10
     */
    @Select("select * from gd_shoppingcart where userid = #{userid}")
    public List<GdShoppingcart> queryCart(@Param("userid") String userid);

    /** 功能描述:
    *  删除某个购物车个商品
    * @param: [cartid]
    * @return: java.lang.Integer
    * @auther: 贾轶飞
    * @date: 2019/5/8 10:33
    */
    @Delete("delete from gd_shoppingcart where cartid = #{cartid}")
    public Integer delCartGoods(@Param("cartid") Integer cartid);

    /** 功能描述:
    * 添加购物车中的商品
    * @param: [gdShoppingcart]购物车商品参数对象
    * @return: org.gd.order.entity.GdShoppingcart
    * @auther: 贾轶飞
    * @date: 2019/5/8 10:47
    */
    @Insert("insert into gd_shoppingcart values(null,#{comdityId},#{userid},#{num})")
    public Integer addCartGoods(GdShoppingcart gdShoppingcart);

    /** 功能描述:
    * 修改购物车中已有商品的数量
    * @param: [gdShoppingcart]
    * @return: java.lang.Integer
    * @auther: 贾轶飞
    * @date: 2019/5/8 10:50
    */
    @Update("update gd_shoppingcart set num=#{num} where cartid=#{cartid}")
    public Integer updCartGoods(GdShoppingcart gdShoppingcart);

    @Select("select count(*) from gd_shoppingcart where userid =#{userid} and comdityId =#{comdityId}")
    public Integer queryCount(@Param("userid")Integer userid, @Param("comdityId")Integer comdityId);

    @Delete("delete from gd_shoppingcart where userid = #{userid} and comdityId = #{comdityId}")
    public Integer batchDelCart(@Param("comdityId")Integer comdityId,@Param("userid")Integer userid);

    @Select("select count(1) from gd_shoppingcart where userid =#{userid}")
    Integer cartGoodsCount(@Param("userid")Integer userid);
}
