package kr.spring.ch01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		/*
		 * (ClassPathXmlApplicationContext 객체 생성을 통해)
		 * applicationContext.xml 설정 파일을 읽어들여 스프링 컨테이너를 생성
		 */                            
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		//객체를 컨테이너로부터 읽어들임 (컨테이너에 미리 bean 설정을 해두었음)
		//getBean("식별자") : 식별자를 넣어서 그 객체를 읽어와 사용할 수 있게됨.
		MessageBean messageBean = (MessageBean)context.getBean("messageBean");
		messageBean.sayHello("홍길동");
	
		//어플리케이션 종료 시 컨테이너에 존재하는 모든 빈(객체)를 종료 (자원 정리)
		context.close();
		
	}
}
