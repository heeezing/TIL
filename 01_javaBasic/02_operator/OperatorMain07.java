package kr.s02.operator;

public class OperatorMain07 {
	public static void main(String[] args) {
		System.out.println("===비교(관계)연산자===");
		boolean result;
		int a = 10;
		double b = 10.5;
		
		result = a < b;
		System.out.println("a < b : " + result); //true
		
		result = a > b;
		System.out.println("a > b : " + result); //false
		
		result = a <= b;
		System.out.println("a <= b : " + result); //true
		
		result = a >= b;
		System.out.println("a >= b : " + result); //false
		
		result = a == b;
		System.out.println("a == b : " + result); //false
		
		result = a != b;
		System.out.println("a != b : " + result); //true
	}
}
