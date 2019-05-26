package org.gd.order.fegin;


import org.fresh.gd.commons.consts.api.wx.GdWxUserService;
import org.fresh.gd.commons.consts.api.wx.user.GdWxUserManageService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 贾轶飞
 * @dat e2019/5/24 15:19
 */
@FeignClient("authorization-service")
public interface OrderFeginToUser extends GdWxUserService {
}
