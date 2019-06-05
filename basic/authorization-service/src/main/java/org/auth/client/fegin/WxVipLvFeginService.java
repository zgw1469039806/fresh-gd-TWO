package org.auth.client.fegin;

import org.fresh.gd.commons.consts.api.vip.VipLvService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 贾轶飞
 * @date 2019/5/31 14:10
 */
@FeignClient("vip-service")
public interface WxVipLvFeginService extends VipLvService {
}
