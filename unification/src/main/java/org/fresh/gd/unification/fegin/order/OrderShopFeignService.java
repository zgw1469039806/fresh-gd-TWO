package org.fresh.gd.unification.fegin.order;

import org.fresh.gd.commons.consts.api.order.GDOrderShopService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/14 15:37
 * @Description:
 */
@FeignClient("order-service")
public interface OrderShopFeignService extends GDOrderShopService {

}
