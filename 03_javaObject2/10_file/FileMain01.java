package kr.s10.file;

import java.io.File;

public class FileMain01 {
	public static void main(String[] args) {
		///간단한 탐색기 기능
		//경로 지정
		String path = "C:\\"; //C디렉토리
		
		File f = new File(path);
		
		//명시한 path가 실제 시스템 안에 존재하는지, 혹은 디렉토리가 맞는지 여부 확인
		if(!f.exists() || !f.isDirectory()) { 
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0); //프로그램 종료
		}
		
		//지정한 경로의 하위 디렉토리와 파일 정보를 File 배열로 반환
		File[] files = f.listFiles(); 
		
		for(int i=0 ; i<files.length ; i++) {
			File f2 = files[i];
			if(f2.isDirectory()) { //디렉토리인 경우
				System.out.println("[" + f2.getName() + "]"); //디렉토리명 반환
			}else { //파일인 경우
				System.out.print(f2.getName()); //파일명 반환
				System.out.printf("(%,dbytes)\n", f2.length()); //File클래스의 length()메서드는 용량을 반환.
			}
		}
	}
}
