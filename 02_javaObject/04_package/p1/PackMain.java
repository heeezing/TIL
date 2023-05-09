package kr.s04.p1;

import kr.s04.p2.PackTwo;

public class PackMain {
	public static void main(String[] args) {
		//클래스를 호출할 때는 패키지를 같이 명시해서 식별함 (원칙)
		kr.s04.p1.PackOne p1 = new kr.s04.p1.PackOne();
		
		//같은 패키지의 클래스를 호출해서 객체 생성을 할 때는 패키지 생략 가능.
		PackOne p2 = new PackOne();
		
		//다른 패키지의 클래스를 호출할 때는 반드시 패키지를 명시해야함.
		kr.s04.p2.PackTwo p3 = new kr.s04.p2.PackTwo();
		
		//import문을 이용해서 사용할 클래스를 등록하면 패키지 생략 가능.
		PackTwo p4 = new PackTwo();
	}
}
