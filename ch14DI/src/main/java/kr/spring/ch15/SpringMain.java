package kr.spring.ch15;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//컨테이너 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
		
		//[프로퍼티 타입]을 이용한 의존 관계 자동 설정
		SystemMonitor monitor = (SystemMonitor)context.getBean("systemMonitor");
		System.out.println(monitor);
		
		//자원 정리
		context.close();
	}
}
