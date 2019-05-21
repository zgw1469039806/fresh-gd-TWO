package org.gd.order.mapper;

import org.apache.ibatis.annotations.*;
import org.fresh.gd.commons.consts.pojo.dto.order.*;
import org.gd.order.entity.GdOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-04-12
 */
@Mapper
public interface GdOrderMapper extends BaseMapper<GdOrder> {

    int insertOrder(GdOrder gdOrder);

    /** 功能描述:
    * 客户端订单生成
    */
    @Insert("insert into gd_order values(#{orderid},#{vipid},#{ordermeans},#{ordertype},#{ordertype},#{ordermoney}," +
            "#{orderStat},#{orderTime},#{storeid},#{belongStoreNam},#{userid},#{priceml},#{address},#{phone})")
    Integer addOrder(GdWxOrderAndShopDTO gdWxOrderAndShopDTO);

    Integer orderCount(OrderCountDTO orderCountDTO);

    List<GdOrderDTO> selOrderPage(OrderPageDTO orderPageDTO);

    @Update("update gd_order set orderStat = #{ordStart} where orderid = #{orderId}")
    Integer updOrderStartById(OrderStartDTO orderStartDTO);

}
