package com.goblin.annotations;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMonitor {

    /**
     * 自定义日志方法描述
     *
     * @return 方法描述
     */
    String methodDesc() default "";

    /**
     * 是否需要打印方法返回值
     *
     * @return boolean
     */

    boolean printResult() default true;
}
