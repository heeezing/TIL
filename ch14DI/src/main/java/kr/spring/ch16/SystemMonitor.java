package kr.spring.ch16;

import org.springframework.beans.factory.annotation.Autowired;

public class SystemMonitor {
	//프로퍼티
	private int periodType;
	private SmsSender sender;
	
	public int getPeriodType() {
		return periodType;
	}
	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}
	public SmsSender getSender() {
		return sender;
	}
	
	/*
	 * @Autowired 어노테이션
	 *  - 타입을 이용해서 자동적으로 프로퍼티 값을 설정.
	 *    따라서 해당 타입의 bean 객체가 존재하지 않거나 2개 이상 존재할 경우,
	 *    스프링은 @Autowired 어노테이션이 적용된 bean 객체를 생성할 때 예외를 발생시킴.
	 * 	- 생성자, 필드, 메서드에 지정 가능.
	 *    메서드에 지정할 때는 setXXX 뿐만 아니라 일반 메서드에도 적용 가능.
	 *    
	 * @Autowired(required=false)로 지정하면 해당 타입의 bean 객체가 존재하지 않더라도 예외가 발생하지 않음.
	 * 기본값은 @Autowired(required=true)
	 */
	@Autowired
	public void setSender(SmsSender sender) {
		this.sender = sender;
	}
	
	
}
