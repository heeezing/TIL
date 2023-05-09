package kr.s11.input;

import java.io.IOException;

public class StandardInputMain02 {
	public static void main(String[] args) {
		try {
			int input = 0;
			//명시적으로 -1을 만들려면 ctrl+z 입력
			while((input=System.in.read())!=-1) { //더이상 읽어올 게 없으면 -1이 됨.
				System.out.println("input:" + input + ",(char)input:" + (char)input);
			}
			System.out.println("프로그램 종료");
		}catch(IOException e) {
			e.printStackTrace(); //예외 문구 출력
		}	
	}
}
