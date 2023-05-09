package kr.s03.operation;

public class ForMain05 {
	public static void main(String[] args) {
		System.out.println("===for문===");
	
		//다중 for문
		for(int dan=2 ; dan<=9 ; dan++) {
			//단을 2부터 9까지 지정
			System.out.println("** " + dan + "단 **");
			for(int i=1; i<=9 ; i++) {
				//i는 곱해지는 수(1~9)
				System.out.println(dan + "*" + i + "=" + dan * i);
			}
		}
	}
}
