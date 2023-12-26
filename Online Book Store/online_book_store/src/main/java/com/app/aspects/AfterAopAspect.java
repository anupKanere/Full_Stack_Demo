package com.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class AfterAopAspect {

	@Pointcut("execution(* com.app.service.*.*(..))")
	public void test() {
		
	}
	
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(value = "test()", returning = "result123")
	public void afterReturning(JoinPoint joinPoint, Object result123) {
		log.info("After Ret advice : {} returned successfuly : with value {}", joinPoint.getSignature(), result123);
	}

	@After(value = "test()")
	public void after(JoinPoint joinPoint) {
		log.info("After advice : after execution of {}", joinPoint.getSignature());
	}

	@AfterThrowing(value = "test()", throwing = "exc")
	public void afterThrowing(Exception exc) {
		log.info("After throwing advice : Exception occurred 123456 : " + exc);
	}
	
	@Around(value = "test()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Proceed with the original method execution
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info(
            "Method {} executed in {}ms",
            joinPoint.getSignature().toShortString(),
            executionTime
        );

        return result;
    }
	
}