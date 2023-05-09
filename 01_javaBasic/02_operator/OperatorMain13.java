package kr.s02.operator;

public class OperatorMain13 {
	public static void main(String[] args) {
		System.out.println("===대입연산자===");
		int a = 5;
		int b = 7;
		
		a += b; //a = a + b;
		System.out.println("a += b : " + a); //5+7=12
		
		a -= b; //a = a - b;
		System.out.println("a -= b : " + a); //12-7=5
		
		a *= b; //a = a * b;
		System.out.println("a *= b : " + a); //5*7=35
		
		a /= b; //a = a / b;
		System.out.println("a /= b : " + a); //35/7=5
		
		a %= b; //a = a % b;
		System.out.println("a %= b : " + a); //5%7=5
	}
}