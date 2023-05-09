package kr.s17.poly;

//부모 클래스
class Parent2 {
	int a = 10;
	public void make() {
		System.out.println("부모 클래스의 make");
	}
}

//자식 클래스
class Child2 extends Parent2 {
	int b = 20;
	//메서드 재정의
	@Override
	public void make() {
		System.out.println("자식 클래스의 make");
	}
}

public class PolyMain03 {
	public static void main(String[] args) {
		Child2 ch = new Child2();
		ch.make();
		System.out.println("---------------");
		Parent2 p = ch; // 자식클래스타입->부모클래스타입 형변환
		//재정의가 되어 있는 메서드는 부모클래스 타입으로 형변환을 해도
		//자식 클래스에 재정의된 메서드가 호출
		p.make();
	}
}
