package kr.s22.finaltest;

class A {
	//멤버 상수 : 지정한 값은 변경 불가능
	final int NUM = 10; //객체에 포함됨. 객체 생성해야 사용 가능.
	//static 상수
	//값도 못 바꾸고, 공유의 개념을 가지기 때문에 일반적으로 public형태로 많이 쓰인다.
	public static final int NUMBER = 20; //객체에 포함 안 됨.
}

public class FinalMain01 {
	public static void main(String[] args) {
		A ap = new A();
		
		//상수는 변경 불가능
		//ap.NUM = 200; -> 오류
		
		System.out.println(ap.NUM);
		
		System.out.println(A.NUMBER); //static-객체와 안 써도 가능
		
		//지역 상수
		final int NO = 30;
		System.out.println(NO);
	}
}
