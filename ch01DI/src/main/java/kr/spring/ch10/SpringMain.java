package kr.spring.ch10;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		//List 타입 프로퍼티 설정
		PerformanceMonitor monitor = (PerformanceMonitor)context.getBean("performanceMonitor");
		System.out.println(monitor);
		
		//자원 정리
		context.close();
	}
}
