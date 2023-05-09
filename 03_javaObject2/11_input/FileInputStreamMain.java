package kr.s11.input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamMain {
	public static void main(String[] args) {
		FileInputStream fis = null;
		int readbyte;
		byte[] readbyte2;
		
		try {
			//파일로부터 데이터를 읽어오기 위해 FileInputStream 객체를 생성
			fis = new FileInputStream("file.txt");
			
			//.read()->파일의 정보를 한 문자씩 읽어들여 아스키코드로 반환.
			//영문 이외의 문자는 깨짐.(1바이트가 넘으므로)
			/*
			while((readbyte=fis.read())!=-1) {
				System.out.println((char)readbyte);
			}
			*/
			
			//영문 이외의 문자도 처리 가능
			//.available():읽어오는 데이터를 바이트로 환산해주는 메서드 (FileInputStream에 존재)
			readbyte2 = new byte[fis.available()]; 
			//파일로부터 읽어들인 데이터를 byte[]에 저장
			fis.read(readbyte2);
			                  //byte 배열을 String으로 변환
			System.out.println(new String(readbyte2));
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) { //IOException이 상위이므로 아래에 위치.
			e.printStackTrace();
		}finally {
			//자원 정리
			if(fis!=null) try {fis.close();} catch(IOException e) {}
		}
	}
}
