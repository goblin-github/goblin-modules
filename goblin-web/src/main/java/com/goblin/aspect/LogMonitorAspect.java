package com.goblin.aspect;

import com.goblin.annotations.LogMonitor;
import com.goblin.utils.GsonUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 日志切面
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
@Aspect
@Component
public class LogMonitorAspect {
    private static final String KEY = "requestId";

    private static final Logger logger = LoggerFactory.getLogger(LogMonitorAspect.class);

    @Pointcut("@within(com.goblin.annotations.LogMonitor) || @annotation(com.goblin.annotations.LogMonitor)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogMonitor logMonitor = getLogMonitor(joinPoint, signature);
        String methodDesc = getMethodDesc(logMonitor, joinPoint, signature);
        MDC.put(KEY, UUID.randomUUID().toString());
        logger.info("methodDesc:{},params:{}", methodDesc, getMethodParams(signature, joinPoint));
        Object result = joinPoint.proceed();
        stopWatch.stop();
        if (logMonitor.printResult()) {
            logger.info("methodDesc:{},result:{},cost:{}", methodDesc, GsonUtils.toJson(result), stopWatch.getTotalTimeMillis());
        } else {
            logger.info("methodDesc:{},cost:{}", methodDesc, stopWatch.getTotalTimeMillis());
        }
        return result;
    }

    /**
     * 获取目标注解，如果方法上有注解，优先使用方法注解
     *
     * @param joinPoint 切入点
     * @param signature 方法
     * @return LogMonitor
     */
    private LogMonitor getLogMonitor(ProceedingJoinPoint joinPoint, MethodSignature signature) {
        LogMonitor methodLogMonitor = signature.getMethod().getAnnotation(LogMonitor.class);
        if (Objects.nonNull(methodLogMonitor)) {
            return methodLogMonitor;
        }
        return joinPoint.getTarget().getClass().getAnnotation(LogMonitor.class);
    }

    private String getMethodDesc(LogMonitor methodLogMonitor, ProceedingJoinPoint joinPoint, MethodSignature signature) {
        return StringUtils.isNotBlank(methodLogMonitor.methodDesc()) ? methodLogMonitor.methodDesc() : joinPoint.getTarget().getClass().getName().concat(":").concat(signature.getName());
    }

    private String getMethodParams(MethodSignature signature, ProceedingJoinPoint joinPoint) {
        Map<String, Object> parameters = Maps.newHashMap();
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return GsonUtils.toJson(parameters);
        }
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            parameters.put(parameterNames[i], args[i]);
        }
        return GsonUtils.toJson(parameters);
    }

}
