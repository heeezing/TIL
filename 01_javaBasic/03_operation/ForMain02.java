package kr.s03.operation;

public class ForMain02 {
	public static void main(String[] args) {
		System.out.println("===for문===");
		
		for(int i=5 ; i>=1 ; i--) {
			System.out.print(i + "\t");
		}		
		System.out.println("\n-------------");
		
		for(int i=0 ; i<=10 ; i+=2) {
			System.out.print(i + "\t");
		}
		System.out.println("\n-------------");
		
		for(int i=0 ; i<=10 ; i++) {
			if (i%2==0) { //i의 값이 짝수인 경우 출력
				System.out.print(i + "\t");
			}
		}
	}
}
