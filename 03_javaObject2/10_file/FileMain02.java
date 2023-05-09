package kr.s10.file;

import java.io.File;
import java.io.IOException;

public class FileMain02 {
	public static void main(String[] args) {
		//절대 경로 지정 (전체 경로를 다 명시)
		//String path = "C:\\javaWork\\sample.txt"; //javawork폴더에 sample.txt를 만들려고 한다.
		
		//상대 경로 지정 (전체 경로를 다 명시하지 않고 일부 경로 혹은 파일명만 지정)
		String path = "sample.txt"; //프로젝트를 기본 경로로 인식하여 저장.
		
		File f1 = new File(path);
		System.out.println("===파일 생성===");
		
		try {
			System.out.println(f1.createNewFile()); //제공된 경로를 기반으로 파일 생성
			                    //파일이 생성되면 true, 생성되지 않으면 false 반환
			                    //경로가 잘못되면 IOException 발생
		}catch(IOException e) {
			e.printStackTrace(); //예외문구출력
		}
		
		System.out.println("===파일의 정보===");
		System.out.println("절대 경로 : " + f1.getAbsolutePath()); //절대 경로 반환
		//절대 경로 : C:\javaWork\workspace\ch03-javaObject2\sample.txt
		System.out.println("상대 경로 : " + f1.getPath()); //상대 경로 반환
		//상대 경로 : sample.txt
		System.out.println("디렉토리명 : " + f1.getParent()); //파일이 포함되어져 있는 디렉토리명(부모 경로) 반환
		//디렉토리명 : null //상대 경로로 지정했기 때문에 불러올 수 X. 절대 경로로 지정했다면 읽어옴.
		System.out.println("파일명 : " + f1.getName()); //이름(파일명or디렉토리명) 반환
		//파일명 : sample.txt	
	}
}
