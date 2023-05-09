package kr.s02.list;

import java.util.Vector;

public class VectorMain01 {
	public static void main(String[] args) {
		Vector<String> v = new Vector<String>();
		//Vector에 객체 저장
		v.add("김연아");
		v.add("이상화");
		v.add("손연재");
		v.add("김연경");
		
		//Vector에 저장된 요소의 목록
		System.out.println(v);
		System.out.println("------------");
		
		//반복문을 이용한 출력
		for(int i=0 ; i<v.size(); i++) {
			System.out.println(v.get(i));
		}
		System.out.println("------------");
		
		//확장 for문을 이용한 출력
		for(String name : v) {
			System.out.println(name);
		}
	}
}
