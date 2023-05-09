package kr.s05.overloading;

public class OverloadingMain01 {
	/*
	 * Method Overloading (중복 정의)
	 * :하나의 클래스 내에서 같은 이름을 가지는 메서드가 여러 개 정의되는 것.
	 * 메서드명은 동일하게 하고 인자의 타입/갯수/배치순서가 다를 경우 다른 메서드로 인식함.
	 */
	public void print(int n) {
		System.out.println("정수 n = " + n);	
	}
	
	public void print(double a) {
		System.out.println("실수 a = " + a);	
	}
	
	public void print(double a, long n) {
		System.out.println("실수 a = " + a + ", 정수 n = " + n);	
	}
	
	public void print(long n, double a) {
		System.out.println("정수 n = " + n + ", 실수 a = " + a);	
	}
	
	public static void main(String[] args) {
		OverloadingMain01 om = new OverloadingMain01(); 
		om.print(10);
		om.print(15.23);
		om.print(2.7, 235L);
		om.print(123L, 5.8);
		
	}
}
