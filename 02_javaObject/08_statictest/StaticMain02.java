package kr.s08.statictest;

public class StaticMain02 {
	//instance 변수
	int a;
	//static 변수
	static String s;
	
	public static void main(String[] args) {
		//인스턴스 변수는 객체 생성 후 호출해야 함.
		//System.out.println("a = " + a); - 오류
		
		//static 변수는 객체 생성과 무관하며 호출 시 static 영역에 만들어짐
		StaticMain02.s = "고래의 꿈";
		//s = "고래의 꿈"; 도 가능
		
		//main이 static변수와 같은 클래스에 정의되어 있기 때문에
		//클래스명을 생략하고 사용 가능
		System.out.println("s = " + s);
		
	}
}
