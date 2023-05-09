package kr.s02.operator;

public class OperatorMain08 {
	public static void main(String[] args) {
		System.out.println("===논리연산자===");
		boolean a, b, result;
		a = true;
		b = false;
		
		//논리곱(&&)
		//주어진 조건들이 모두 true일 때 결과값이 true
		result = a && b;
		System.out.println("a && b = " + result); // false
		
		//논리합(||)
		//주어진 조건 중 하나라도 true이면 결과값이 true
		result = a || b;
		System.out.println("a || b = " + result); //true
		
		//부정(!)
		//부정을 하면 논리값을 반대로 바꿈
		result = !a;
		System.out.println("!a = " + result); //false
	}
}
