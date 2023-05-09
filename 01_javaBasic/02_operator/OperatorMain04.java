package kr.s02.operator;

public class OperatorMain04 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 
		 * 534자루의 연필을 30명의 학생들에게 똑같은 개수로 나눠 줄 때
		 * 학생 당 몇 개를 가질 수 있고,
		 * 최종적으로 몇 개가 남는 지를 구하시오.
		 * 
		 * [출력 예시]
		 * 학생 한 명이 가지는 연필 수 : **
		 * 남은 연필 수 : **
		 */
		
		int pencil = 534, student = 30;
		int i1 = pencil / student;
		int i2 = pencil % i1;
		System.out.println("학생 한 명이 가지는 연필 수 : " + i1);
		System.out.println("남은 연필 수 : " + i2);
//		System.out.printf("학생 한 명이 가지는 연필 수 : %d%n", i1);
//		System.out.printf("남은 연필 수 : %d%n", i2);	
	}
}
