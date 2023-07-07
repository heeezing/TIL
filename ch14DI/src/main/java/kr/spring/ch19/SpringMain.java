package kr.spring.ch19;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//컨테이너 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_scan.xml");
		
		//@Component 어노테이션을 이용한 bean객체 생성
		HomeController home = (HomeController)context.getBean("homeController");
		System.out.println(home);
		
		//자원 정리
		context.close();
	}
}
