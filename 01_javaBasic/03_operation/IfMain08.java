package kr.s03.operation;

public class IfMain08 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 입력 받은 3개의 정수 중 최대값, 최소값 구하시오.
		 */
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		
		System.out.print("정수 a : ");
		int a = input.nextInt();
		
		System.out.print("정수 b : ");
		int b = input.nextInt();
		
		System.out.print("정수 c : ");
		int c = input.nextInt();
	
		//최대값 구하기
		int max = a; //조건 체크를 위한 기준 값을 설정
		if(b > max) max = b;
		if(c > max) max = c;
		
		//최소값 구하기
		int min = a;// 조건 체크를 위한 기준 값을 설정
		if(b < min) min = b;
		if(c < min) min = c;
		
		System.out.println("최대값은 " + max + "입니다.");
		System.out.println("최소값은 " + min + "입니다.");
		
		input.close();
		
	}
	
}
//		내가 한 것... 맞긴 한데 너무 복잡했다	

//		if (a >= b && a >= c) {
//			max = a;
//		}else if (b >= a && b >= c) {
//			max = b;
//		}else {
//			max = c;
//		}
//		
//		if (a <= b && a <= c) {
//			min = a;
//		}else if (b <= a && b <= c) {
//			min = b;
//		}else {
//			min = c;
//		}
//		
//		System.out.println("최대값 : " + max);
//		System.out.println("최소값 : " + min);
//		
//		input.close();
		

