package kr.s03.operation;

public class ForMain03 {
	public static void main(String[] args) {
		System.out.println("===for문===");
		//반복문을 이용해서 값을 누적하고, 누적된 값을 저장하는 변수를 선언
		int total = 0;
		for(int i=1 ; i<=5 ; i++) {
			//누적
			total += i;
			System.out.println("1부터 " + i + "까지 자연수의 합은 " + total + "이다.");
		}
		
		System.out.println("--------------");
		//누적된 값이 저장되는 변수 선언
		int sum = 0;
		for(int i=1 ; i<=100 ; i++) {
			//누적
			sum += i;
		}
		System.out.println("1부터 100까지의 합 : " + sum);
	}
}
