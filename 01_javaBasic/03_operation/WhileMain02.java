package kr.s03.operation;

public class WhileMain02 {
	public static void main(String[] args) {
		System.out.println("===while문===");
		int sum = 0, su = 1; //초기값 지정
		
		while(su <= 100) { //조건식
			sum += su;
			su++;
		}
		System.out.println("1부터 100까지의 합 : " + sum);
		
	}
}
