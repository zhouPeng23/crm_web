package com.linkknown.crm.common.aspect.paramslog;

import java.lang.annotation.*;

/**
 * @author zhoupeng
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebParamsLog {

    /**
     * 日志描述信息
     */
    public String description() default "";
}
