package kr.s19.lang;

public class WrapperMain01 {
	public static void main(String[] args) {
		//기본자료형 데이터 ;변수를 만들어서 넣는 것이기 때문에 메서드 x
		boolean b = true;
		
		//참조자료형 데이터
		Boolean wrap_b = new Boolean(b); //->deprecated되어서 사용하지 않음.
		//기본자료형 데이터를 참조자료형 데이터로 변환
		//auto boxing
		Boolean wrap_b2 = b;
		
		//참조자료형 데이터를 기본자료형 데이터로 변환
		boolean b2 = wrap_b2.booleanValue();
		System.out.println(b2);
		
		System.out.println(wrap_b2);
	}
}