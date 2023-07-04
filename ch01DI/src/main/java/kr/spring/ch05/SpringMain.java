package kr.spring.ch05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//컨테이너에 DI 생성자 설정 방식으로 생성된 개체를 읽어들임
		MemberService memberService = (MemberService)context.getBean("memberService");
		memberService.send();
		
		//어플리케이션 종료 시 컨테이너에 존재하는 모든 bean객체를 종료 (자원 정리)
		context.close();
	}
}
