package kr.s23.enumtest;

//열거형 상수
enum Lesson {
	JAVA, ORACLE, HTML
}

public class EnumMain02 {
	public static void main(String[] args) {
		//가장 보편적인 사용 형태
		System.out.println(Lesson.JAVA);  //JAVA
		System.out.println(Lesson.ORACLE);  //ORACLE
		System.out.println(Lesson.HTML);  //HTML
		System.out.println("---------");
		
		//열거 객체의 문자열을 반환
		System.out.println(Lesson.JAVA.name());  //JAVA
		System.out.println(Lesson.ORACLE.name());  //ORACLE
		System.out.println(Lesson.HTML.name());  //HTML
		System.out.println("---------");
		
		//열거 객체의 순번(0부터 시작)을 반환
		System.out.println(Lesson.JAVA.ordinal());  //0
		System.out.println(Lesson.ORACLE.ordinal());  //1
		System.out.println(Lesson.HTML.ordinal());  //2
	}
}
