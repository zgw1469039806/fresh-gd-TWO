package org.gd.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fresh.gd.commons.consts.pojo.dto.order.GdOrderDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.OrderCountDTO;
import org.fresh.gd.commons.consts.pojo.dto.order.OrderPageDTO;
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

    //List<>


    Integer orderCount(OrderCountDTO orderCountDTO);

    List<GdOrderDTO> selOrderPage(OrderPageDTO orderPageDTO);
}
