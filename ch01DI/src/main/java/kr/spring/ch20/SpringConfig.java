package kr.spring.ch20;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바 코드 기반 설정
@Configuration //자바 코드가 설정 파일 역할을 하게 하는 어노테이션
public class SpringConfig {
	//bean 객체 설정 ( @Bean("빈이름") 과 같이 bean이름을 지정할 수 있음.)
	@Bean  //(객체가 생성되면, 만들어진 객체를 컨테이너에 넣어주는 역할을 하는 어노테이션)
	public Worker worker() { //메서드 명이 bean의 이름이 됨
		return new Worker();
	}
	
	@Bean
	public Executor executor() {
		return new Executor();
	}
}
