package kr.spring.ch18;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//컨테이너 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_annot.xml");
		
		//@Resource 어노테이션을 이용한 프로퍼티 설정
		HomeController home = (HomeController)context.getBean("homeController");
		System.out.println(home);
		
		//자원 정리
		context.close();
	}
}
