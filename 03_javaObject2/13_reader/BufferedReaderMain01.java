package kr.s13.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BufferedReaderMain01 {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			//InputStreamReader는 System.in(바이트 입력 스트림)을 
			//문자 입력 스트림으로 변환 시킴. 그리고 BufferedReader에 넘겨주는 것.
			//그럼 BufferedReader가 갖고 있는 readLine()을 이용해서 String으로 데이터를 받음.
			//이 과정에서 오류 발생 가능 있으므로 IOException 의무적 명시. -> 다 연결.
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("당신의 이름:");
			String name = br.readLine();
			System.out.println(name);
			
			System.out.print("당신의 나이:");
			          //String->int 변환
			int age = Integer.parseInt(br.readLine());
			System.out.println(age);
			
		}catch(NumberFormatException e) {  //숫자를 잘못 입력시 오류 발생 가능(선택적 명시)
			e.printStackTrace();
		}catch(IOException e) {  //readLine에서 오류 발생 가능(의무적 명시)
			e.printStackTrace();
		}finally {
			//자원 정리
			if(br!=null) try{br.close();} catch(IOException e) {}
		}
	}
}
