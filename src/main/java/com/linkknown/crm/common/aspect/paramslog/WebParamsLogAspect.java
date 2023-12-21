package com.linkknown.crm.common.aspect.paramslog;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author zhoupeng
 */
@Aspect
@Order(2)
@Component
public class WebParamsLogAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static ThreadLocal<Integer> logid = ThreadLocal.withInitial(() -> (int)((Math.random() * 9 + 1) * 10000000));

    @Pointcut("@annotation(com.linkknown.crm.common.aspect.paramslog.WebParamsLog)")
    public void webParamsLogPointCut(){}

    @Around("webParamsLogPointCut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 开始打印请求日志
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取 @webParamsLog3 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);
        // 打印请求相关参数
        log.info("logid:{},==================== 请求开始 ====================",logid.get());
        // 打印描述信息
        log.info("logid:{}, =====> Description     : {}", logid.get(),methodDescription);
        // 打印请求 url
        log.info("logid:{}, =====> URL             : {}", logid.get(),request.getRequestURL().toString());
        // 打印 Http method
        log.info("logid:{}, =====> HTTP Method     : {}", logid.get(),request.getMethod());
        // 打印调用 api 的全路径以及执行方法
        log.info("logid:{}, =====> Class Method    : {}", logid.get(),joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("logid:{}, =====> IP              : {}", logid.get(),request.getRemoteAddr());
        // 打印请求入参
        log.info("logid:{}, =====> Request params  : {}", logid.get(),JSON.toJSONString(joinPoint.getArgs()));

        //执行切点，执行切点后，会去依次调用 @Before -> 接口逻辑代码 -> @After -> @AfterReturning
        Object res = joinPoint.proceed();

        //打印出参
        log.info("logid:{}, =====> Response params :{}", logid.get(),JSON.toJSONString(res));
        //执行耗时
        log.info("logid:{}, =====> Time-consuming  :{}", logid.get(),System.currentTimeMillis()-startTime);
        log.info("logid:{},==================== 请求结束 ====================",logid.get());
        return res;
    }


    /**
     * 获取切面注解的描述
     * @param joinPoint 切点
     * @return 描述信息
     */
    private String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebParamsLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
    

}
