package kr.s07.constructor;

class Car {
	String color; //색상
	String gearType; //변속기 종류 - auto(자동), manual(수동)
	int door; //문의 개수
	
	//생성자(생략 가능. 생략하면 컴파일 시 자동 생성.)
	//객체 생성 시 호출되고 멤버 변수를 초기화하는 역할
	public Car() {} 
}

public class CarMain01 {
	public static void main(String[] args) {
		//객체 생성 시 생성자는 처음에 한 번만 실행되고
		//객체 생성 이후 호출 불가능
		Car c1 = new Car();
		System.out.println(c1.color + "," + c1.gearType + "," + c1.door); 
		System.out.println("--------------");
		
		c1.color = "white";
		c1.gearType = "auto";
		c1.door = 4;
		
		System.out.println(c1.color + "," + c1.gearType + "," + c1.door); 
		System.out.println("--------------");
		
		Car c2 = new Car();
		System.out.println(c2.color + "," + c2.gearType + "," + c2.door);
		System.out.println("--------------");
		
		c2.color = "red";
		c2.gearType = "manual";
		c2.door = 5;
		
		System.out.println(c2.color + "," + c2.gearType + "," + c2.door);
	}
}
