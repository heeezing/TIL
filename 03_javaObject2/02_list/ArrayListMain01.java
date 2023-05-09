package kr.s02.list;

import java.util.ArrayList;

class A {
	@Override
	public String toString() {
		return "A클래스";
	}
}
class B {}

public class ArrayListMain01 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		//ArrayList에 객체 저장 (클래스 타입이기 때문에 메서드를 통해 주소를 저장)
		list.add(new A()); //toString() 재정의  //A -> Object 형변환 되어 저장
		list.add(new B()); //재정의 X           //B -> Object 형변환 되어 저장
		list.add("박문수"); //기본 값을 넣은 것 같지만 auto boxing으로 인해 
		list.add(100);    //각각 String, Integer객체에 들어간 것.(String,Integer -> Object)
		
		//ArrayList에 저장된 요소의 목록
		System.out.println(list);
		//[A클래스, kr.s02.list.B@73a28541, 박문수, 100]

	}
}
