package kr.s13.reader;

import java.io.FileNotFoundException; //파일을 읽어야 하는데 파일이 없는 경우
import java.io.FileReader;
import java.io.IOException;

public class FileReaderMain {
	public static void main(String[] args) {
		FileReader fr = null;
		int readChar;
		try {
			fr = new FileReader("file.txt");
			//파일로부터 데이터를 한 문자씩 읽어들여 유니코드로 반환(2바이트 입력 처리)
			                //파일이 딱 끝나는 지점에서 .read()메서드가 -1을 반환함.
			while((readChar = fr.read())!=-1) {
				System.out.print((char)readChar);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			if (fr!=null) try{fr.close();} catch(IOException e) {}
		}
	}
}
