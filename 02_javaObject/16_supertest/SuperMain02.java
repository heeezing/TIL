package kr.s16.supertest;

///생성자에 인자가 없을 때 -> 따로 명시하지 않아도 암묵적으로 호출 / super();

//부모 클래스
class Parent {
	int a = 100;
	//생성자
	public Parent() {
		//부모 클래스(Object)의 default 생성자를 호출
		super();
	}
}

//자식 클래스
class Student extends Parent {
	int b = 200;
	//생성자
	public Student() {
		//부모 클래스(Parent)의 default 생성자를 호출
		super();
	}
}

public class SuperMain02 {
	public static void main(String[] args) {
		Student s = new Student();
		System.out.println(s.a);
		System.out.println(s.b);
	}
}
