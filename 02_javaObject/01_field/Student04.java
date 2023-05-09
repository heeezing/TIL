package kr.s01.field;

public class Student04 {
	//멤버 변수 
	String name;
	int korean;
	int english;
	int math;
	int sum;
	double average;
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		Student04 student = new Student04();
		//객체의 멤버 변수 값 초기화
		student.name = "홍길동";
		student.korean = 99;
		student.english = 95;
		student.math = 97;
		student.sum = student.korean + student.english + student.math;
		student.average = student.sum / 3.0;
		
		System.out.println("이름 : " + student.name);
		System.out.println("국어 : " + student.korean);
		System.out.println("영어 : " + student.english);
		System.out.println("수학 : " + student.math);
		System.out.println("총점 : " + student.sum);
		System.out.printf("평균 : %.2f" , student.average);
	}
}
