package com.springaop.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class AspectManagement {

    @Around("execution(* com.springaop.example.service..*(..)) && target(bean)") //execute in the whole service package
    public Object logStartAndEnd(ProceedingJoinPoint jp, Object bean) throws Throwable {
        final String className = bean.getClass().getSimpleName();
        final String methodName = jp.getSignature().getName();
        final Object[] args = jp.getArgs();
        log.info("Starting method: {} in class: {} with args: {}", methodName, className, args);
        final Object result = jp.proceed();
        if (Objects.nonNull(result)) {
            log.info("End method: {} in class: {} return value: {}", methodName, className, result);
        } else {
            log.info("End void method: {} in class: {}", methodName, className);
        }
        return result;
    }

    @Around("@annotation(LogExecutionTime) && target(bean)") //execute by @LogExecutionTime
    public Object logPerformance(ProceedingJoinPoint jp, Object bean) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = jp.proceed();
        stopWatch.stop();
        final long executionTime = stopWatch.getTotalTimeMillis();
        final String methodName = jp.getSignature().getName();
        final String className = bean.getClass().getSimpleName();
        log.info("Execution time {} ms for method: {} in class: {}", executionTime, methodName, className);
        return result;
    }

    @Before("@annotation(EmailAllowed)") //execute by @EmailAllowed
    public void process() {
        if (!isAllowedEmail()) {
            //we can throw exception if is not return true
            throw new RuntimeException("User does not allow for email!");
        }
    }

    private boolean isAllowedEmail() {
        //we got user id from token,
        //we let's think return false when call user client for email allow
        return false;
    }
}
