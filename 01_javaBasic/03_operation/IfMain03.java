package kr.s03.operation;

public class IfMain03 {
	public static void main(String[] args) {
		System.out.println("===다중if문===");
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("정수 한 개 입력:");
		int a = input.nextInt();
		
		//다중if문
		if (a > 0) {
			System.out.println(a + "는 양수입니다.");
		}else if (a < 0) {
			System.out.println(a + "는 음수입니다.");
		}else {
			System.out.println("0입니다.");		
		}
		
		input.close();
	}
}
