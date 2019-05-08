package org.fresh.gd.unification.controller.management;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogHdDTO;
import org.fresh.gd.unification.aspectj.GdLog;
import org.fresh.gd.unification.fegin.management.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @DATA 2019-05-08 14:20
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@Api(description = "操作日志记录查询")
@Slf4j
@RestController
@RequestMapping("/unification")
public class ManaLogController
{
    @Autowired
    LogService  logService;

    @PostMapping("/gdLogs")
    public List<GdLogHdDTO> gdLogs(@RequestBody ResponseData<GdLogDTO> gdLog)
    {
        GdLogDTO gdLogDTO=gdLog.getData();
        return logService.selByName(gdLogDTO);
    }
}
