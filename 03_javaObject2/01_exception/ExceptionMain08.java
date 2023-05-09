package kr.s01.exception;

import java.util.Scanner;

//사용자 정의 예외 클래스
class NegativeNumberUseException extends Exception{ 
	//생성자
	public NegativeNumberUseException(String str) {
			super(str);  //str -> Exception으로 전달
	}
}

public class ExceptionMain08 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("0이상만 입력:");
		try {
			int a = input.nextInt();
			if(a>=0) {
				System.out.println("입력한 숫자:" + a);
			}else { 
				 //사용자가 정의한 예외를 인위적으로 발생시킴
				 throw new NegativeNumberUseException("음수를 사용할 수 없습니다.");		
			}
		}catch(NegativeNumberUseException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("예외 발생");
		}finally {
			input.close();  //정상 입력이던 예외이던 동작되게끔 finally 생성.
		}
	}
}
