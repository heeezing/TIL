package kr.s03.method;

public class MethodMain02 { 
	/*
	 * [실습]
	 * 입력한 int형 정수값이 음수이면 -1을, 0이면 0을,
	 * 양수이면 1을 반환하는 signOf메서드를 작성하시오.
	 */

	//멤버 메서드
	public int signOf(int n) {
			int sign = 0;			
			if (n<0) {
				sign = -1;
			}else if(n>0) {
				sign = 1;
			}		
			return sign;
	}
	
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);		
		System.out.print("정수 입력:");
		int a = input.nextInt();
		
		//객체 선언 및 생성 - 해야 메서드가 메모리에 올라감
		MethodMain02 me = new MethodMain02();
		int result = me.signOf(a);
		
		System.out.println("signOf(a)는 " + result + "입니다.");
		
		input.close();
	}
}
