package org.managment.service.fegin;

import org.fresh.gd.commons.consts.api.vip.VipInSetService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/26 16:09
 * @Description:
 */

@FeignClient("vip-service")
public interface ToVipFegin extends VipInSetService {

}
