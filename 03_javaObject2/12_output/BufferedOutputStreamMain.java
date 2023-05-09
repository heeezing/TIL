package kr.s12.output;

import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class BufferedOutputStreamMain {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;		
		try {
			fos = new FileOutputStream("bufferOut.txt");
			bos = new BufferedOutputStream(fos);
			
			String str = "BufferedOutputStream Test입니다.";
			
			//buffer에 문자열데이터를 저장
			         //String->byte[] 변환
			bos.write(str.getBytes());
			
			//버퍼를 비우고 버퍼의 데이터를 파일에 출력
			bos.flush();
			
			System.out.println("파일을 생성하고 파일에 내용을 기술함.");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			//BufferedOutputStream의 close메서드는
			//자원정리하기 전에 buffer를 체크해서 남아있는 데이터가 있으면 flush 처리함.
			if(bos!=null) try {bos.close();} catch(IOException e) {}
			if(fos!=null) try {fos.close();} catch(IOException e) {}
			//bos에서 fos를 계속 액세스하고 있기 때문에 fos를 먼저 닫아버리면 오류 발생 가능.
			//따라서 나중에 생성된 bos를 먼저 닫아주자. (역순으로 종료)
		}
	}
}
