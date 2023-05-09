package kr.s01.basic;
public class PrintMain06 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 
		 * System.out.printf()를 이용해서 아래와 같이 출력하시오.
		 * 
		 * 국어 : 98점
		 * 영어 : 97점
		 * 총점 : 195점
		 * 평균 : 97.5점
		 */
		
		//방법 1
		System.out.printf("%s : %d점%n", "국어", 98);
		System.out.printf("%s : %d점%n", "영어", 97);
		System.out.printf("%s : %d점%n", "총점", 195);
		System.out.printf("%s : %.1f점%n", "평균", 97.5);
		System.out.println("==========");
		
		//방법 2
		System.out.printf("%s : %d점%n%s : %d점%n%s : %d점%n%s : %.1f점", "국어", 98, "영어", 97, "총점", 195, "평균", 97.5);
		
		}
}

