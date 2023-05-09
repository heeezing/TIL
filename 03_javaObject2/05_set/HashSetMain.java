package kr.s05.set;

import java.util.HashSet;
import java.util.Iterator; //HashSet안에 데이터를 뽑아내는 메서드가 없어서 Iterator의 메서드 활용

public class HashSetMain {
	public static void main(String[] args) {
		String[] array = {"Java", "jsp", "Java", "Oracle"};
		
		HashSet<String> hs = new HashSet<String>();
		
		//HashSet에 문자열 객체 저장
		for(String n : array) {
			hs.add(n); //중복 불허
		}
		//HashSet 요소의 목록
		System.out.println(hs);  //[Java, jsp, Oracle]
		System.out.println("-------------------");
		
		//Iterator을 이용한 출력
		Iterator<String> ir = hs.iterator();  //hs를 Iterator로 가져옴
		//Iterator에 저장된 객체가 있는지 검증
		while(ir.hasNext()) {
			//검증된 객체를 반환
			System.out.println(ir.next());
		}
		System.out.println("-------------------");
		
		//확장 for문을 이용한 출력
		for(String s : hs) {
			System.out.println(s);
		}
	}
}
