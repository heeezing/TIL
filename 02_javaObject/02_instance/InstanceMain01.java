package kr.s02.instance;

public class InstanceMain01 {
	//클래스의 기본 구조 
	
	//멤버 필드 (객체에 들어감)
	int var1; //멤버 변수
	String var2; //멤버 변수
	
	//생성자 (명시하지 않아도 컴파일 시 자동 생성)
	public InstanceMain01() {}
	
	//멤버 메서드 (객체에 들어감)
	//        메서드명 인자or파라미터
	public int sum(int a, int b) {
		return a + b;
	//         작업(동작)
	}
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		//  자료형   참조변수 객체생성연산자 생성자
		InstanceMain01 me = new InstanceMain01(); //참조변수에 주소가 저장됨.
		//객체의 멤버 변수 값 초기화
		me.var1 = 1000;
		me.var2 = "서울";
		//객체의 멤버 메서드 호출
		int result = me.sum(20,30);
		
		System.out.println(me.var1);
		System.out.println(me.var2);
		System.out.println(result);
	}
}
