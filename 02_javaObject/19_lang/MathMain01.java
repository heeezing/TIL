package kr.s19.lang;

public class MathMain01 {
	public static void main(String[] args) {
		//절대값
		int a = Math.abs(-10);
		System.out.println("절대값 : " + a); //절대값 : 10

		//올림
		double b = Math.ceil(3.3);
		System.out.println("올림 : " + b); //올림 : 4.0
		
		//절삭
		double c = Math.floor(3.7);
		System.out.println("절삭 : " + c); //절삭 : 3.0
		
		//반올림
		int d = Math.round(3.7f); //int로 받으려면 float, long으로 받으려면 double 입력
		System.out.println("반올림 : " + d); //반올림 : 4
		
		//최대값
		int e = Math.max(3, 5); //같은 자료형끼리만 비교 가능.
		System.out.println("최대값 : " + e); //최대값 : 5
		
		//최소값
		int f = Math.min(4, 7);  //같은 자료형끼리만 비교 가능.
		System.out.println("최소값 : " + f); //최소값 : 4
	}
}
