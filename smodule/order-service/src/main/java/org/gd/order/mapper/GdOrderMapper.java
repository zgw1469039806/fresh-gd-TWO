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

    /**
     * 功能描述:
     * 客户端订单生成
     */
    @Insert("insert into gd_order values(#{orderid},#{vipid},#{ordermeans},#{ordertype},#{ordertype},#{ordermoney},#{orderStat},#{orderTime},#{storeid},#{belongStoreNam},#{userid},#{priceml},#{address},#{phone})")
    Integer addOrder(GdWxOrderAndShopDTO gdWxOrderAndShopDTO);

    Integer orderCount(OrderCountDTO orderCountDTO);

    List<GdOrderDTO> selOrderPage(OrderPageDTO orderPageDTO);

    @Update("update gd_order set orderStat = #{ordStart} where orderid = #{orderId}")
    Integer updOrderStartById(OrderStartDTO orderStartDTO);

    List<GdUserOrderDTO> userOrderQuery(GdUserOrderDTO gdUserOrderDTO);

    @Select(" select count(1) from gd_order where userid=#{userid} and orderStat =#{orderStat}")
    Integer queryCountOrder(@Param("userid")Integer userid,@Param("orderStat")Integer orderStat);

    @Delete("delete from gd_order where orderid=#{orderid}")
    Integer removeOrder(@Param("orderid")String orderid);
    @Update("update gd_order set recipients=#{recipients},phone=#{phone},address=#{address} where orderid =#{orderid}" +
            " and userId")
    Integer updPayOrder(GdUserOrderDTO gdUserOrderDTO);

    /**
     * 根据年根和月份订单
     *
     * @Date: 11:49 2019/5/24
     * @Author: 郭家恒
     */
    GdDeportFormDTO QueryDeportForm(GdDeportFormDTO gdDeportFormDTO);

    /**
     * 查询指定年份的订单
     * @Date: 13:26 2019/5/24
     * @Author: 郭家恒
     */
    List<GdOrder> SelOrderByYear(@Param("selYear") String selYear,@Param("selMonse") String Monse,@Param("storeid") String storeid);
}
