package kr.s06.map;

import java.util.Hashtable;
import java.util.Enumeration;

public class HashtableMain {
	public static void main(String[] args) {
		Hashtable<String,String> h = new Hashtable<String,String>();
		//Hashtable에 key와 value의 쌍으로 데이터 저장.
		h.put("name", "홍길동");
		h.put("age", "27");
		h.put("tel", "010-1234-5678");
		h.put("hobby", "영화 감상");
		h.put("job", "경찰");
		//key의 중복은 가능하지만 중복 시 마지막에 명시한 value가 인정됨.
		h.put("name", "홍길순");
		//key와 value에 null 불인정
		//h.put("address", null);
		//h.put(null, "서울");
		
		//Hashtable의 key와 value 데이터 목록
		System.out.println(h);
		System.out.println("----------------");
		
		String name = h.get("name");
		String age = h.get("age");
		System.out.println(name + "," + age);
		System.out.println("----------------");
		
		Enumeration<String> en = h.keys();
		while(en.hasMoreElements()) {
			String key = en.nextElement(); //key 반환
			System.out.println(key + "," + h.get(key));
		}
		
		
	}
}
