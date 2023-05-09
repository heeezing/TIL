package kr.s01.exception;

public class ExceptionMain03 {      //Run Configuration을 통해 데이터 입력
	public static void main(String[] args) {
		int var = 50;
		
		//다중 catch문
		//예외가 발생하면 예외 객체가 전달되는 catch블럭으로 이동해서 수행문 실행
		try {		  //String타입 숫자 -> int타입 숫자
			int data = Integer.parseInt(args[0]);
			System.out.println(var/data);
			System.out.println("------");
			/*
			 * (주의) 다중 catch문 사용
			 * Exception과 하위 예외 클래스를 동시에 명시할 때는
			 * 하위 예외 클래스를 먼저 명시하고 가장 뒤에 Exception을 명시해야
			 * 동작 상의 문제가 발생하지 않음.
			 */
		}catch(NumberFormatException e) {  //숫자가 아닌 문자를 입력했을 시
			System.out.println("숫자가 아닙니다.");
		}catch(ArrayIndexOutOfBoundsException e) {  //args에 데이터 입력 안 했을 시
			System.out.println("입력한 데이터가 없습니다.");
		}catch(ArithmeticException e) {  //데이터를 0으로 입력했을 시 (0으로 나누는 연산 = 에러)
			System.out.println("0으로 나눌 수 없습니다.");
		}catch(Exception e) {  //이외 다른 예외 발생 시
			System.out.println("나머지 예외는 여기로 오세요.");
		}
		System.out.println("프로그램 끝!");
	}
}
