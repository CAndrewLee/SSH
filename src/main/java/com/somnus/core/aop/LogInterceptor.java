package com.somnus.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {
//	@Pointcut("execution(public * com.spring.aop.service..*.add(..))")
	public void myMethod(){};
	
//	@Before("myMethod()")
	public void before() {
		System.out.println("method before");
	}
	
//	@AfterReturning("myMethod()")
	public void AfterReturning() {
		System.out.println("method after returning");
	}
	
//	@AfterThrowing("myMethod()")
	public void AfterThrowin() {
		System.out.println("method after throwin");
	}
	
	
//	@Around("myMethod()")
	public void aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method around start");
		pjp.proceed();
		System.out.println("method around end");
	}
	
}
