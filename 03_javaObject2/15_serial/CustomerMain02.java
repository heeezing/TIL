package kr.s15.serial;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class CustomerMain02 {
	public static void main(String[] args) {
		System.out.println("===역직렬화하기===");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("object.ser");
			ois = new ObjectInputStream(fis);
			
			//역직렬화 수행
			//new를 통해 객체를 생성한게 아니라, 파일로부터 객체를 읽어와서 생성 후 그 객체의 주소를 m에 반환
			Customer m = (Customer)ois.readObject(); 
			System.out.println(m);
			
		}catch(FileNotFoundException e) { //파일이 없을 경우
			e.printStackTrace();
		}catch(IOException e) { //입출력 오류의 경우
			e.printStackTrace();
		}catch(ClassNotFoundException e) { //시스템 내에 해당 클래스가 없을 경우
			e.printStackTrace();
		}finally {
			if(ois!=null) try {ois.close();} catch(IOException e) {}
			if(fis!=null) try {fis.close();} catch(IOException e) {}
		}
	}
}
