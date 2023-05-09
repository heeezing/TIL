package kr.s01.exception;

public class ExceptionMain04 {
	public static void main(String[] args) {
		int var = 50;
		try {
			int data = Integer.parseInt(args[0]);
			System.out.println(var/data);
			System.out.println("------");
		}catch(Exception e) {
			//instanceof 연산자를 이용해서 타입 체크
			if(e instanceof NumberFormatException) {
				System.out.println("숫자가 아닙니다.");
			}else if(e instanceof ArrayIndexOutOfBoundsException) {
				System.out.println("입력한 데이터가 없습니다.");
			}else if(e instanceof ArithmeticException) {
				System.out.println("0으로 나눌 수 없습니다.");
			}else {
				System.out.println("나머지 예외는 여기로 오세요.");
			}
		}
		System.out.println("프로그램 종료!");
	}
}
