package kr.s02.operator;

public class OperatorMain09 {
	public static void main(String[] args) {
/*
 논리곱 (&&)
 선조건 && 후조건    결과값
 true    true    true
 true    false   false
 true    false   false
 false--->미실행   false
 
  논리합 (||)
  선조건 || 후조건   결과값
  true--->미실행   true
  false   true   true
  false   false  false 
 */
		System.out.println("===논리연산자===");
		//증감연산자, 비교연산자, 논리연산자
		int a, b;
		a = b = 10;
		
		boolean c = a++ >= ++b && ++a > b++;
		//           10    11     실행할 필요 X
		//             false       (미실행)
		System.out.println(a + "," + b); //11,11
		//(a는 10이었지만 다음 단계-출력에서 1증가)
		System.out.println(c); //false
		
		System.out.println("===============");
		
		int d, e;
		d = e = 10;
		
		boolean f = ++d < e++ || d++ >= ++e;	
		//           11   10      11    12
		//            false        false
		System.out.println(d + "," + e); //12,12
		//(a는 11이었지만 다음 단계-출력에서 1증가)
		System.out.println(f); //false
	}
}
