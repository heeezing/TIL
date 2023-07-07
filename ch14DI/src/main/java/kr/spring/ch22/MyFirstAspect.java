package kr.spring.ch22;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//공통 기능이 구현되는 클래스
@Aspect  //반드시 있어야 어노테이션을 인식, 동작됨
public class MyFirstAspect {
	/*
	 * [구현 가능한 Advice 종류] - 언제 공통 기능을 핵심 기능에 적용할지 정의할 수 있다.
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 * 			종류				|							설명
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 *  @Before					| 대상 객체의 메서드(핵심 기능) 호출 전에 공통 기능을 실행
	 *  Before Advice			|	
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 *  @AfterReturning			| 대상 객체의 메서드(핵심 기능)가 예외 없이 실행되면 그 후에 공통 기능을 실행
	 *  After Returning Advice	|	
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 *  @AfterThrowing			| 대상 객체의 메서드(핵심 기능)를 실행하는 도중에 예외가 발생하면 공통 기능을 실행
	 *  After Throwing Advice	|	
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 *  @After					| 대상 객체의 메서드(핵심 기능)를 실행하는 도중의 예외 여부와 상관 없이, 메서드 실행 후 무조건 공통 기능을 실행
	 *  After Advice			| (try~catch~finally의 finally 블럭과 비슷)
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 *  @Around					| 대상 객체의 메서드 실행 전/후 또는 예외 발생 시점에 공통 기능을 실행
	 *  Around Advice			|	
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 */
	
	//핵심 기능을 검색해서 지정하는 역할
	@Pointcut("execution(public String launch())")
	public void getPointcut() {}
	
	//@Before("getPointcut()")
	public void before() {
		//메서드(핵심 기능) 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! **메서드가 호출되기 전에 나온다!");
	}
	
	//@AfterReturning(value="getPointcut()",returning="msg")
	public void afterReturning(String msg) {
		//호출된 메서드가 예외를 내보내지 않고 정상적으로 종료됐을 때 동작하는 어드바이스
		System.out.println("Hello AfterReturning! **메서드가 호출한 후에 나온다! 전달된 객체 : " + msg);
	}
	
	//@AfterThrowing(value="getPointcut()",throwing="ex")
	public void afterThrowing(Exception ex) {
		//메서드 호출이 예외를 던졌을 때 동작하는 어드바이스
		System.out.println("Hello AfterThrowing! **예외가 생기면 나온다! 예외 : " + ex);
	}
	
	//@After(value="getPointcut()")
	public void after() {
		//메서드 종료 후에 동작하는 어드바이스 (예외 여부와 상관 X)
		System.out.println("Hello After! **메서드가 호출된 후에 나온다!");
	}
	
	@Around("getPointcut()")
	public String around(ProceedingJoinPoint joinPoint) throws Throwable{
		//메서드 호출 전후에 동작하는 어드바이스
		System.out.println("Hello Around before! **메서드가 호출되기 전에 나온다!");
		String s = null;
		try {
			//핵심 기능이 수행된 후 데이터 반환
			s = (String)joinPoint.proceed(); //핵심기능을 가져와(joinPoint) 메서드를 실행해서 결과값을 가져옴(proceed)
				//반환 타입을 지정(ex.String)하면 이 기능 후에 실행되는 공통 기능에서 반환 데이터를 전달받아 사용할 수 있다.
				//다음에 사용하지 않게 하려면 void타입으로 지정!
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Hello Around after! **메서드가 호출된 이후에 나온다! 반환된 객체 : " + s);
		}
		
		return s;
	}
	
}
