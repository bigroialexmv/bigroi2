package com.bigroi.shop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Before("execution(* *(..)) && (within(com.bigroi.shop.dao..*) || within(com.bigroi.shop.service..*) )")
	public void logBefore(JoinPoint jp) {
		
		String packageName = jp.getSignature().getDeclaringTypeName();
	    String methodName = jp.getSignature().getName();
	    
		logger.trace(" *** Executing method: " + packageName + "." + methodName);
		Object[] signatureArgs = jp.getArgs();
		   for (Object signatureArg: signatureArgs) {
			   logger.trace("Arg: " + signatureArg);
		   }
		
		
	}
	
	@After("execution(* *(..)) && (within(com.bigroi.shop.dao..*) )")
	public void logAfter(JoinPoint joinPoint) {
		logger.trace(" *** Finished executing method: " + joinPoint.getSignature().getName());
	}

	

}
