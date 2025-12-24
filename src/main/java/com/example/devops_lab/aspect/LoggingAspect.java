package com.example.devops_lab.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class LoggingAspect {

    private final HttpServletRequest request;
    private final ThreadLocal<Boolean> exceptionThrown = ThreadLocal.withInitial(() -> false);

    @Pointcut("execution(public * com.example.devops_lab.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        if (request != null) {
            log.info("Request: IP: {}, URL: {}, HTTP Method: {}, Controller Method: {}, {}",
                    request.getRemoteAddr(),
                    request.getRequestURI(),
                    request.getMethod(),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
        }
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        log.info("Controller Method: {}, {} Execution time: {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                elapsedTime);

        return result;
    }

    @After("log()")
    public void after(JoinPoint joinPoint) {
        if (exceptionThrown.get()) {
            exceptionThrown.set(false);
            return;
        }

        log.info("Controller Method: {}, {} - Successfully executed",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "log()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        exceptionThrown.set(true);

        log.error("Exception in Controller Method {}, {} with argumnts {}. Error message {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()),
                ex.getMessage());
    }

}
