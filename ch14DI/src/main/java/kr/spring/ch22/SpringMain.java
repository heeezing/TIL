package kr.spring.ch22;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.spring.product.Product;

public class SpringMain {
	public static void main(String[] args) {
		//컨테이너 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_aop2.xml");
		
		//핵심 기능이 구현된 메서드 호출
		Product p = (Product)context.getBean("product");
		p.launch();
		
		//자원 정리
		context.close();		
	}
}
