package kr.s17.poly;

//부모 클래스
class Parent {
	int a = 100;
}

//자식 클래스
class Child extends Parent {
	int b= 200;
}

public class PolyMain01 {
	public static void main(String[] args) {
		/*
		 * 다형성(Polymorphism)
		 * :여러가지 형태를 가질 수 있는 능력
		 * 자바에서는 한 타입의 참조변수로 여러 타입의 객체를 참조할 수 있도록 함으로써
		 * 다형성을 프로그램적으로 구현.
		 * 부모클래스 타입의 참조 변수로 자식클래스의 객체를 참조.
		 */
		Child ch = new Child();
		System.out.println(ch.a);
		System.out.println(ch.b);
		
		Parent p = ch; //ch에 저장된 객체의 주소를 p에 저장
		//자식클래스 타입 -> 부모클래스 타입으로 형 변환
		//업캐스팅, 자동적으로 형 변환
		//참조자료형을 부모클래스 타입으로 명시하면 부모클래스 영역의 멤버만 호출 가능
		System.out.println(p.a);
		//호출 범위가 줄어들어 호출 불가능
		//System.out.println(p.b); -> 오류
		
		Child ch2 = (Child)p;
		//부모클래스 타입 -> 자식클래스 타입으로 형 변환
		//다운캐스팅, 명시적으로 형 변환
		System.out.println(ch2.a);
		System.out.println(ch2.b);
	}
}
