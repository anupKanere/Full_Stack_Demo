package com.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Slf4j
public class UserAccessAspect {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@Before("execution(* com.app.controller.*.*(..))")
	public void before(JoinPoint joinPoint) {
		//Advice 
		log.info("Check for user access ");
		log.info("Allowed excecution for {}",joinPoint);
	}
}
