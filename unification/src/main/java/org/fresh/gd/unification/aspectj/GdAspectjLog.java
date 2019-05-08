package org.fresh.gd.unification.aspectj;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.fresh.gd.commons.consts.utils.VeDate;
import org.fresh.gd.unification.fegin.management.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @DATA 2019-05-08 09:33
 * @Author 张国伟  WeChat:17630376104
 * @Description AOP增强
 */
@Aspect
@Component
public class GdAspectjLog {

    @Autowired
    LogService logService;

    /**
     * 功能描述
     * 定义切点 @Pointcut
     * 在注解的位置切入代码
     *
     * @return void
     * @author zgw
     */
    @Pointcut("@annotation(org.fresh.gd.unification.aspectj.GdLogClass)")
    public void logPoinCut() {
    }

    /**
     * 功能描述
     * 切面 配置通知
     *
     * @return
     * @author zgw
     */
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint)
    {
        //保存日志
        GdLog gdLog = new GdLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        GdLogClass myLog = method.getAnnotation(GdLogClass.class);
        if (myLog != null)
        {
            String value = myLog.value();
            //保存获取的操作
            gdLog.setOperation(value);
        }
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        gdLog.setMethod(className + "." + methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].getClass().getName().equals(OAuth2Authentication.class.getName()))
            {
                OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) args[i];
                gdLog.setUsername("" + oAuth2Authentication.getPrincipal());
            }
        }

        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        gdLog.setParams(params);
        gdLog.setCreateDate(VeDate.getStringDate());
        logService.save(gdLog);
    }

}
