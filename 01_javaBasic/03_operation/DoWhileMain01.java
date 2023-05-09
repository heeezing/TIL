package kr.s03.operation;

public class DoWhileMain01 {
	public static void main(String[] args) {
		System.out.println("===do~while문===");
		
		int su = 0;
		String str = "Java DoublePlus";
		
		//선 처리, 후 비교
		do {
			System.out.println(str);  //6번 출력
		}while(su++ < 5); //(주의)semicolon 생략 시 오류!
		
		System.out.println("--------------");
		
		int su2 = 0;
		
		//선 비교, 후 처리
		while(su2++ < 5) {
			System.out.println(str);  //5번 출력
		}
	}
}