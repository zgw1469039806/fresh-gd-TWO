package org.fresh.gd.unification.fegin.management;
import org.fresh.gd.commons.consts.pojo.RequestData;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.GdLogHdDTO;
import org.fresh.gd.commons.consts.pojo.dto.management.GdStoreDTO;
import org.fresh.gd.commons.consts.pojo.dto.oauth.UserDTO;
import org.fresh.gd.unification.aspectj.GdLog;
import org.fresh.gd.unification.fegin.auth.UserFeignService;
import org.fresh.gd.unification.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



/**
 * @DATA 2019-05-08 10:47
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@Service
public class LogService {
    @Autowired
    LogMapper logMapper;

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    ManaFeginService manaFeginService;


    public String save(GdLog gdLog) {
        Integer integer = logMapper.saveLog(gdLog);
        if (integer > 0) {
            return "添加成功";
        }

        return "Fail";
    }

    /**
     * 功能描述
     *  根据用户名 查询用户ID  和用户所在店铺 返回操作日志
     * @return java.util.List<org.fresh.gd.commons.consts.pojo.dto.management.GdLogHdDTO>
     * @author zgw
     */
    public List<GdLogHdDTO> selByName(GdLogDTO gdLogDTO) {
        List<GdLogHdDTO> logHdDTOS = logMapper.selAll(gdLogDTO);
        if (logHdDTOS.size() == 0)
        {
            return null;
        }
        RequestData<List<GdLogHdDTO>> requestData = new RequestData<>();
        requestData.setData(logHdDTOS);
        ResponseData<List<UserDTO>> listResponseData = userFeignService.selLogByUserId(requestData);
        List<UserDTO> dtoList = listResponseData.getData();
        List<Integer> uids = new ArrayList<>();
        RequestData<List<Integer>> integerRequestData = new RequestData<>();
        for (UserDTO userDTO : dtoList) 
        {
            uids.add(Integer.parseInt(userDTO.getIsnoYg()));
        }
        integerRequestData.setData(uids);


        ResponseData<List<GdStoreDTO>> listResponseData1 = manaFeginService.QueryByid(integerRequestData);
        for (GdLogHdDTO gdLogHdDTO : requestData.getData()) 
        {
            for (UserDTO userDTO : listResponseData.getData()) 
            {
                if (gdLogHdDTO.getUsername().equals(userDTO.getUseraccount()))
                {
                    for (GdStoreDTO storeDTO : listResponseData1.getData())
                    {
                        gdLogHdDTO.setStorename(storeDTO.getStorename());
                        break;
                    }
                }
            }
        }
        return logHdDTOS;
    }
}
