package kr.s01.field;

public class Student {
	//멤버 변수 
	String name;
	int age;
	
	public static void main(String[] args) {
		//객체 선언
		//자료형    변수
		Student student;
		//객체 생성
		student = new Student();
		
		//객체의 구성원에 데이터 저장
		//멤버 변수에 값 저장
		student.name = "홍길동";  //.을 찍으면 그 안으로 들어간다고 생각하자
		student.age = 20;
		
		//객체의 멤버 변수 값을 출력
		System.out.println(student.name);
		System.out.println(student.age);
	}
}
