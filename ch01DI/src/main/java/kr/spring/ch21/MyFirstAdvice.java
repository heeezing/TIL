package kr.spring.ch21;

//공통 기능이 구현되는 클래스
public class MyFirstAdvice {
	/*
	 * [구현 가능한 Advice 종류] - 언제 공통 기능을 핵심 기능에 적용할지 정의할 수 있다.
	 * 		종류												설명
	 * Before Advice			|	대상 객체의 메서드(핵심 기능) 호출 전에 공통 기능을 실행
	 * After Returning Advice	|	대상 객체의 메서드(핵심 기능)가 예외 없이 실행되면 그 후에 공통 기능을 실행
	 * After Throwing Advice	|	대상 객체의 메서드(핵심 기능)를 실행하는 도중에 예외가 발생하면 공통 기능을 실행
	 * After Advice				|	대상 객체의 메서드(핵심 기능)를 실행하는 도중의 예외 여부와 상관 없이, 메서드 실행 후 무조건 공통 기능을 실행
	 * 							|	(try~catch~finally의 finally 블럭과 비슷)
	 * Around Advice			|	대상 객체의 메서드 실행 전/후 또는 예외 발생 시점에 공통 기능을 실행
	 */
	public void before() {
		//메서드(핵심 기능) 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! **메서드가 호출되기 전에 나온다!");
	}
	
	public void afterReturning(String msg) {
		//호출된 메서드가 예외를 내보내지 않고 정상적으로 종료됐을 때 동작하는 어드바이스
		System.out.println("Hello AfterReturning! **메서드가 호출한 후에 나온다! 전달된 객체 : " + msg);
		
	}
}
