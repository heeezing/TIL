package kr.s19.lang;

public class WrapperMain02 {
	public static void main(String[] args) {
		//기본자료형 데이터 -> 참조자료형 데이터
		//deprecated되어 사용하지 않음
		Integer obj1 = new Integer(12);
		Integer obj2 = new Integer(7);
		
		//기본자료형 데이터 -> 참조자료형 데이터
		//auto boxing
		Integer obj3 = 12;
		Integer obj4 = 7;
		
		//참조자료형 데이터 -> 기본자료형 데이터
		int result = obj3.intValue() + obj4.intValue();
		System.out.println(result);  //19
		
		//참조자료형 데이터 -> 기본자료형 데이터 (더 간단한 방법)
		//auto unboxing
		int result2 = obj3 + obj4;
		System.out.println(result2);  //19
		
	}
}
