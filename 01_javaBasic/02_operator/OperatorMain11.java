package kr.s02.operator;

public class OperatorMain11 {
	public static void main(String[] args) {
		System.out.println("===조건(삼항)연산자===");
		int a = 5, b = 10;
		int max; //최대값을 저장할 변수 선언
		int min; //최소값을 저장할 변수 선언
		
		//조건(삼항)연산자
		//    조건  참값 거짓값
		max = a>b ? a : b;
		System.out.println("max = " + max);
		
		min = a<b ? a : b;
		System.out.println("min = " + min);
	}
}