package kr.s01.exception;

import java.io.IOException; //lang패키지 아니라서 import해와야함

import java.io.BufferedReader; //둘이 함께 쓰임
import java.io.InputStreamReader;

public class ExceptionMain06 {
	
	//멤버 메서드            //임시 보관했다가 try에 전달 -> 예외 발생 시 catch이동
	public void printData() throws IOException, NumberFormatException { 
		                         //입출력 관련 예외 //숫자가 아닌 것을 입력했을 때
		                         //일반적(명시 필수)//실행 시(선택적 명시)
		/*
		 * throws 예약어의 사용
		 * 메서드에서 예외가 발생할 수 있고, 예외가 발생하면 예외를 임시 보관하기 위해서
		 * throws 예상되는 예외 클래스명 형식으로 메서드에 표시함.
		 * 이렇게 표시하면 메서드가 호출된 곳에 try~catch문을 명시하고 예외 처리하게 됨.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("단 입력:");
		int dan = Integer.parseInt(br.readLine()); //readline()->다 String으로 출력 
		System.out.println(dan + "단");
		System.out.println("--------");
		for(int i=1 ; i<=9 ; i++) {
			System.out.println(dan + "*" + i + "=" + dan*i);
		}
	} 
	
	public static void main(String[] args) {
		ExceptionMain06 ex = new ExceptionMain06();
		try {
			ex.printData();
		}catch(NumberFormatException e) {
			System.out.println("숫자가 아닙니다.");
		}catch(IOException e) {
			System.out.println("입력 시 오류 발생");
		}
	}
}
