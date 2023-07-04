package kr.spring.ch08;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//컨테이너 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		//DI 프로퍼티 설정 방식으로 생성된 객체 호출
		WorkController work = (WorkController)context.getBean("work");
		System.out.println(work);
		
		//어플리케이션 종료 시 컨테이너에 존재하는 모든 bean객체를 종료
		context.close();
	}
}
