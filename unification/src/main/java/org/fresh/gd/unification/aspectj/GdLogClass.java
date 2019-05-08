package org.fresh.gd.unification.aspectj;

import java.lang.annotation.*;

/**
 * @DATA 2019-05-08 09:32
 * @Author 张国伟  WeChat:17630376104
 * @Description  自定义注解
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface GdLogClass {

    String value() default "";
}
