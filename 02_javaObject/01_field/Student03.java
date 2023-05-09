package kr.s01.field;

public class Student03 {
	//멤버 변수 
	String name;
	int age;
	String address;
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		Student03 student = new Student03();
		
		//멤버 변수 초기화
		student.name = "장영실";
		student.age = 20;
		student.address = "서울";
		
		System.out.println(student.name + "," + student.age + "," + student.address);
		
		//객체 선언 및 생성
		Student03 student2 = new Student03();
		
		//멤버 변수 초기화
		student2.name = "홍길동";
		student2.age = 40;
		student2.address = "부산";
		
		System.out.println(student2.name + "," + student2.age + "," + student2.address);
	}
}
