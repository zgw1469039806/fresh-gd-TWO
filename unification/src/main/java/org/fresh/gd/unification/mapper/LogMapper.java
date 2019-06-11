package org.fresh.gd.unification.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogHdDTO;
import org.fresh.gd.unification.aspectj.GdLog;
import java.util.List;

/**
 * @DATA 2019-05-08 10:49
 * @Author 张国伟  WeChat:17630376104
 * @Description TODO
 */
@Mapper
public interface LogMapper extends BaseMapper<GdLog> {

    @Insert("insert into gd_Log(username,operation,method,params,ip,createDate) values(#{username},#{operation},#{method},#{params},#{ip},#{createDate})")
    Integer saveLog(GdLog gdLog);


    List<GdLogHdDTO> selAll(GdLogDTO gdLog);
}
