package org.fresh.gd.commons.consts.pojo.dto.vip;

import lombok.Data;

/**
 * @Auther: 夏乾航 email:xqh151@163.com
 * @Date: 2019/5/13 09:33
 * @Description:
 */
@Data
public class GdVipindetailedDTO {
    /**
     *   会员手机号
     */
    private String vipPhone;

    /**
     *   会员消费类型
     */
    private String vipindetailedtype;

    /**
     *   会员积分交易数量
     */
    private Integer vipindetailednum;

    /**
     *   会员所属店铺
     */
    private Integer storeid;
}
