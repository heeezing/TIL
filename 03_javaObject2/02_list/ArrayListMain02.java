package kr.s02.list;

import java.util.ArrayList;

public class ArrayListMain02 {
	public static void main(String[] args) {
		//List 구조의 특징 : 저장되는 순서를 유지(인덱스 순서대로 차곡차곡), 중복 저장 허용		
		ArrayList list = new ArrayList();
		list.add("홍길동"); //0  String -> Object
		list.add("박문수"); //1  String -> Object
		list.add("장영실"); //2  String -> Object
		list.add("홍길동"); //3  String -> Object
		
		//ArrayList에 저장된 요소의 목록
		System.out.println(list);  //[홍길동, 박문수, 장영실, 홍길동]
		System.out.println("---------------");
		
		//반복문을 이용한 요소 출력
		for(int i=0 ; i<list.size(); i++) {  //list.size() : 방의 개수
			String name = (String)list.get(i);  //저장할 때 String -> Object로 자동적 형변환했기 때문에
			                                    //빼올 때는 다시 Object -> String으로 명시적 형변환해야함.
			System.out.println(name);
		}
	}
}
