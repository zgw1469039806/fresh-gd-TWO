package org.gd.order.mapper;

import org.apache.ibatis.annotations.*;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrdershopDTO;
import org.gd.order.entity.GdOrdershop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单详细表 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Mapper
public interface GdOrdershopMapper extends BaseMapper<GdOrdershop> {
    /**
     * 功能描述:
     * 插入订单详细
     *
     * @param: []
     * @return: int
     * @auther: 郭家恒
     * @date: 2019/4/25 16:08
     */
    int insertOrderShop(List<GdOrdershopDTO> list);

    @Select("select * from gd_ordershop where orderid = #{orderId}")
    List<GdOrdershopDTO> selOrderShopById(String orderId);

    /** 功能描述:
    * 移除订单中的商品
    * @param: []
    * @return: java.lang.Integer
    * @auther: 贾轶飞
    * @date: 2019/5/25 18:36
    */
    @Delete("delete from gd_ordershop where orderid = #{orderid}")
    Integer removeOrderGoods(@Param("orderid")String orderid);

    /** 功能描述:
    * 购物车中的商品数量
    * @param: [userid]
    * @return: java.lang.Integer
    * @auther: 贾轶飞
    * @date: 2019/5/26 8:30
    */

}
