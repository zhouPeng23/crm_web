package com.linkknown.crm.common.aspect.exception;

import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Aspect
@Order(1)
@Component
public class WebExceptionServiceAspect {

    private Logger logger = LoggerFactory.getLogger(WebExceptionServiceAspect.class);

    @Pointcut("@within(com.linkknown.crm.common.aspect.exception.WebExceptionService)")
    public void webExceptionPointCut() {}

    @Around("webExceptionPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            return point.proceed();

        } catch (Exception e) {
            return this.handleException(e);
        }
    }

    /**
     * 处理异常
     * @param e 异常
     * @return object
     */
    private Object handleException(Exception e) {
        if (e instanceof WebException) {
            BaseResponse response = ((WebException) e).getResponse();
            logger.info("==========>crm exception ,code:{}，msg:{}",response.getCode(),response.getMsg());
            logger.error("错误信息:",e);
            return response;
        } else {
            logger.info("==========>crm exception ,code:{}，msg:{}",ResponseEnum.web_is_busy.getCode(),ResponseEnum.web_is_busy.getMsg());
            logger.error("错误信息:",e);
            return BaseResponse.fail(ResponseEnum.web_is_busy);
        }
    }
}
