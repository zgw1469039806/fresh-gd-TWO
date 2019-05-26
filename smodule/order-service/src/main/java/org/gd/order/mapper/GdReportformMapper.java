package org.gd.order.mapper;

import org.gd.order.entity.GdReportform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 统计报表 Mapper 接口
 * </p>
 *
 * @author guowei.zhang
 * @since 2019-05-24
 */
@Mapper
public interface GdReportformMapper extends BaseMapper<GdReportform> {

    List<GdReportform> selRfByoid(List<String> list);
}
