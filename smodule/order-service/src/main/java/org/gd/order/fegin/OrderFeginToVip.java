package org.gd.order.fegin;

import org.fresh.gd.commons.consts.api.vip.VipService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/10 17:20
 * @Description:
 */
@FeignClient("vip-service")
public interface OrderFeginToVip extends VipService {
}
