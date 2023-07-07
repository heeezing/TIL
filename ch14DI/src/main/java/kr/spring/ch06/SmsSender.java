package kr.spring.ch06;

public class SmsSender {
	//toString() 재정의
	//(원래 object가 가지고 있는 toString()은 재정의하지 않으면 참조값을 출력.)
	@Override
	public String toString() {
		return "SmsSender 호출";
	}
}
