package kr.s14.writer;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterMain {
	public static void main(String[] args) {
		FileWriter fw = null;
		try {
			//덮어쓰기
			fw = new FileWriter("fileWriter.txt");
			String message = "안녕하세요 FileWriter 테스트입니다.";
			//데이터를 버퍼에 저장 (FileWriter도 버퍼 기능이 있음.)
			fw.write(message);
			//buffer를 비우고 buffer의 데이터를 파일로 전송
			fw.flush();
			System.out.println("파일을 생성하고 내용을 기술");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fw!=null) try {fw.close();} catch(IOException e) {}
		}
	}
}
