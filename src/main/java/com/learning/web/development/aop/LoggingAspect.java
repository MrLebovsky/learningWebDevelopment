package com.learning.web.development.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.learning.web.development.annotations.LogExecutionTime)")
    private Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.debug(joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }

    @AfterThrowing(pointcut = "@annotation(com.learning.web.development.annotations.ExceptionNotify)", throwing = "exp")
    private void sendNotifyMail(JoinPoint joinPoint, Exception exp) {
        logger.debug("Got the exception in method: " +
                joinPoint.getSignature() + ":\n" + exp.getLocalizedMessage());
    }
}