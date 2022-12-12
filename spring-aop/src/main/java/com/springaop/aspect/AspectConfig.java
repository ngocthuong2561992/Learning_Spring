package com.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class AspectConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    around,before,after: advice
//    "* com.springaop.aspect.controller.*.*(..))": Pointcut
//    callDaoFailed(): joinPoint
    @Around("execution(* com.springaop.aspect.controller.*.*(..))") //Pointcut
    public void AroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("\n**********************************************************************************************************");
        joinPoint.proceed();
        logger.info("\n**********************************************************************************************************\n");
    }

    @Before("execution(* com.springaop.aspect.dao.TestDAO.callDaoSuccess(..))")
    public void before(JoinPoint joinPoint) {
        logger.info(" before called " + joinPoint.toString());
    }

    @After("execution(* com.springaop.aspect.dao.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info(" after called " + joinPoint.toString());
    }

    @AfterReturning("execution(* com.springaop.aspect.dao.*.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        logger.info(" afterReturning called " + joinPoint.toString());
    }

    @AfterThrowing("execution(* com.springaop.aspect.dao.*.*(..))")
    public void afterThrowing(JoinPoint joinPoint) {
        logger.info(" afterThrowing called " + joinPoint.toString());
    }

    @Around("execution(* com.springaop.aspect.dao.*.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();
        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
        joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
    }

    @Around("@annotation(com.springaop.aspect.TrackTime)")
    public void aroundTrackTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();
        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
        joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
    }
}
