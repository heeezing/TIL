package kr.s10.file;

import java.io.File;

public class FileMain03 {
	public static void main(String[] args) {
		//상대경로 지정
		String path = "sample.txt"; //원래 파일명
		String new_path = "example.txt"; //새 파일명
		
		File f1 = new File(path); //원래 파일의 정보를 갖는 File객체
		File f2 = new File(new_path); //새 파일의 정보를 갖는 File객체
		
		System.out.println("===파일명 변경===");
		
		//renameTo() : 파일명 변경 가능 시 파일명을 변경하고 true 반환,
		//             파일명 변경 불가 시 false 반환.
		System.out.println(f1.renameTo(f2));
	}
}
