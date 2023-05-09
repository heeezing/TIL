package kr.s01.basic;
public class VariableTypeMain04 {
	public static void main(String[] args) {
		System.out.println("===명시적 형변환(강제 형변환)===");
		//더 작은 자료형으로 강등이 일어나는 형태,
		//정보의 손실이 발생할 수 있음. (항상 발생은x)
		byte b1 = 127;
		byte b2 = 127;
		//자동적으로 int로 승격되는 것을 byte로 강등시킴
		byte result1 = (byte)(b1 + b2); //(byte)->캐스트연산자
		System.out.println("result1 = " + result1);
		
		short s1 = 32767;
		short s2 = 32767;
		//자동적으로 int로 승격되는 것을 short로 강등시킴
		short result2 = (short)(s1 + s2); //(short)->캐스트연산자
		System.out.println("result2 = " + result2);
		
		int it1 =1234;
		float f1 = 345.567f;
		//f1의 자료형 float -> int로 강제 형변환
		//데이터 값이 int 안에 들어가지 않아 데이터 값 손실.
		int result3 = it1 + (int)f1;
		System.out.println("result3 = " + result3);
		
		int it2 = 123;
		long lg1 = 567L;
		//lg1의 자료형 long -> int로 강제 형변환
		//데이터 값이 int 안에 들어가므로 데이터 값 손실x
		int result4 = it2 + (int)lg1;
		System.out.println("result4 = " + result4);
		
	}
}
