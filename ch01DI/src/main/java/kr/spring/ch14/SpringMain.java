package kr.spring.ch14;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//컨테이너 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
		
		/* [의존 객체를 자동으로 설정할 수 있는 기능] - bean 객체의 타입이나 이름을 이용
		 *  bean 태그의 autowire 속성에 지정할 수 있는 값
		 * 1) byName : 프로퍼티의 이름과 같은 이름을 갖는 bean 객체를 설정
		 * 2) byType : 프로퍼티의 타입과 같은 타입을 갖는 bean 객체를 설정
		 * 3) constructor : 생성자 파라미터 타입과 같은 타입을 갖는 bean 객체를 생성자에 전달 
		 */
		
		//[프로퍼티 이름]을 이용한 의존 관계 자동 설정 
		WriteArticleService articleService = (WriteArticleService)context.getBean("writeArticleService");
		articleService.write();
		
		//자원 정리
		context.close();
	}
}
