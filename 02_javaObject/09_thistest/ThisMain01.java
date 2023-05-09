package kr.s09.thistest;

public class ThisMain01 {
	//멤버변수 : 클래스 블럭 내에서 정의된 변수
	int b = 200;
	
	public void make() {
		//지역변수 : 메서드,생성자,제어문 블럭 내에서 생성된 변수
		//         해당 블럭이 종료되면 변수는 소멸함.
		int a = 100;
		System.out.println("a : " + a); //지역변수
		System.out.println("b : " + b); //멤버변수
	}
	public void fun() {
		//a변수는 fun()메서드에 정의되지 않아서 호출 불가
		//System.out.println("a : " + a); ->오류 
		System.out.println("b : " + b);
	}
	
	public static void main(String[] args) {
		for(int i=1 ; i<=5 ; i++) { //제어문 블럭 안의 지역 변수
			System.out.print(i + "\t");
		}
		System.out.println("\n-----------------");
		//반복문 안에서 정의된 i는 반복문이 종료되면 소멸됨 -> 호출X
		//System.out.println(i); ->오류
		
		int a; 
		for(a=1 ; a<=5 ; a++) { 
			System.out.print(a + "\t");
		System.out.println("\n-----------------");
		System.out.println(a);
		}
	}		
}
