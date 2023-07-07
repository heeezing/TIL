package kr.spring.ch06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//DI 생성자 전달 방식으로 생성한 객체 호출
		SystemMonitor monitor = (SystemMonitor)context.getBean("monitor");
		System.out.println(monitor); //자동으로 toString()작용, 변수 호출
		
		//어플리케이션 종료 시 컨테이너에 존재하는 모든 bean객체를 종료 (자원 정리)
		context.close();
	}
}
