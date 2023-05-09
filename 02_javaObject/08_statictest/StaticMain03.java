package kr.s08.statictest;

class StaticMethod {
	//인스턴스 변수
	String s1 = "서울";
	//static(클래스) 변수
	static String s2 = "한국";
	
	//static 메서드
	public static String getString() {
		return s2;  //원칙은 StaticMethod.s2 이지만 생략(같은 클래스 내이기 때문)
	}
}

public class StaticMain03 {
	public static void main(String[] args) {
		//static 메서드는 클래스명.메서드명 의 형식으로 호출
		//(같은 클래스가 아니므로 생략 불가)
		System.out.println(StaticMethod.getString());
	}
}
