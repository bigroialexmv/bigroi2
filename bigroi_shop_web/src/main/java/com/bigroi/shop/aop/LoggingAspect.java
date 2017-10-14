package com.bigroi.shop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Before("execution(* *(..)) && (within(com.bigroi.shop.dao..*) || within(com.abc.xyz..dao..*) )")
	public void logBefore(JoinPoint joinPoint) {
		SourceLocation sourceLoc = joinPoint.getSourceLocation();
		logger.trace(" *** Executing method: " 
				+ joinPoint.getSignature().getName() + "(" + sourceLoc.toString() + ")");
		
		
	}
	
	@After("execution(* *(..)) && (within(com.bigroi.shop.dao..*) )")
	public void logAfter(JoinPoint joinPoint) {
		logger.trace(" *** Finished executing method: " + joinPoint.getSignature().getName());
	}

	

}
