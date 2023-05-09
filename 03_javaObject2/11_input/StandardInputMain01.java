package kr.s11.input;

import java.io.IOException;

public class StandardInputMain01 {
	public static void main(String[] args) {
		//자바의 기본 입력
		try {
			System.out.print("영문자 1개 입력:"); 
			//문자 하나를 입력 받아서 아스키코드로 반환
			int a = System.in.read();
			System.out.println(a + "," + (char)a); //ex)A입력시->65,A
			
			//enter처리
			System.in.read(); //\r 13 흡수
			System.in.read(); //\n 10 흡수
			System.out.println("-----------------");
			
			System.out.print("숫자 1개 입력:");
			//입력한 숫자를 문자로 인식해서 아스키코드로 반환하기 때문에 0을 입력하면 48로 반환.
			//따라서 입력한 데이터를 숫자로 바로 사용하고 싶으면
			//입력한 숫자 - 48을 해서 출력할 때 숫자처럼 보여지게 처리함.
			int b = System.in.read() - 48;
			System.out.println(b);
		}catch(IOException e) {
			e.printStackTrace(); //예외 문구 출력
		}
	}
}
