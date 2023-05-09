package kr.s02.list;

import java.util.ArrayList;

public class ArrayListMain03 {
	public static void main(String[] args) {
		//제네릭 표현 : 객체를 생성할 때 객체에 저장할 수 있는 요소의 타입을 지정.
		//            (원래는 다 Object로 형변환하기때문에 서로 다른 값을 넣어도 컴파일 단계에서 오류X.
		//             컴파일 단계에서 오류를 발견하기 위해 미리 지정을 해놓으면 편하다.)
		ArrayList<String> list = new ArrayList<String>();
		list.add("강호동"); // Object가 아닌 String으로 처리. 
		list.add("유재석");  
		//list.add(1000); //  오류
		list.add("박문수"); 
		
		//반복문을 이용한 요소의 출력
		for(int i=0 ; i<list.size(); i++) {
			String name = list.get(i); //제네릭 표현으로 인해 (String)으로 명시적 형변환 할 필요 X.
			System.out.println(name);
		}
		System.out.println("-----");
		
		//확장 for문을 이용한 요소의 출력
		for(String name : list) {
			System.out.println(name);
		}
	}
}
