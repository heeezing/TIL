package kr.s07.constructor;

class Car2{
	String color;
	String gearType;
	int door;
	
	//인자가 없는 생성자 - 기본값으로 초기화
	public Car2() {}
	
	//인자가 있는 생성자
	public Car2(String c, String g, int d) {
		color = c;
		gearType = g;
		door = d;
	}
}

public class CarMain02 {
	public static void main(String[] args) {
		///인자가 없는 생성자 호출 시
		//객체 선언 및 생성
		Car2 c1 = new Car2(); 
		//객체의 멤버 변수에 데이터 할당
		c1.color = "white"; //
		c1.gearType = "auto";
		c1.door = 4;
		System.out.println(c1.color + "," + c1.gearType + "," + c1.door);
		System.out.println("-------------");
		
		///인자가 있는 생성자를 이용해서 객체 생성
		//객체의 멤버 변수를 초기화함
		Car2 c2 = new Car2("blue","auto",2);
		System.out.println(c2.color + "," + c2.gearType + "," + c2.door);
}
}
