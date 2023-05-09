package kr.s12.extention;

//부모클래스
class People {
	public void eat() {
		System.out.println("식사하다");
	}
}
//자식 클래스에 부모클래스를 상속시킴
class Student extends People {
	public void study() {
		System.out.println("공부하다");
	}
}

public class ExtentionMain02 {
	public static void main(String[] args) {
		Student s = new Student();
		s.eat(); //People의 메서드를 상속 받아서 호출 가능 : 식사하다
		s.study(); //Student의 메서드 : 공부하다
		System.out.println(s.toString()); //Object의 메서드를 상속 받아서 호출 가능
		//:kr.s12.extention.Student@73a28541 -> 참조값 호출
	}
}
