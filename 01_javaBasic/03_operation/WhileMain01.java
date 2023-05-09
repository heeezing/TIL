package kr.s03.operation;

public class WhileMain01 {
	public static void main(String[] args) {
		System.out.println("===while문===");
		int i = 1; //초기값 설정
		
		while (i <= 10) { //조건식
//			System.out.println(i); -> 무한 루프 조심
			System.out.println(i++); // 증감식
		}
		
		System.out.println("------------");
		
		int j = 10;
		
		while (j >= 0) {
			System.out.println(j--);
		}
	}
}
