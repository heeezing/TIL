package kr.s16.supertest;

///생성자에 인자가 있을 때 -> 인자와 같은 타입의 값을 넣어 호출 / super(값);

//부모 클래스
class People2 {
	int a;
	//생성자
	public People2(int a) {
		this.a = a;
	}
}

//자식 클래스
class Student2 extends People2{
	//생성자
	public Student2() {
		//부모클래스의 인자 타입이 int인 생성자를 호출
		super(100);
	}
}
public class SuperMain03 {
	public static void main(String[] args) {
		Student2 st = new Student2();
		System.out.println(st.a);
	}
}
