package kr.s17.poly;

//부모 클래스
class Car {
	String color;
	int door;
	
	public void drive() {
		System.out.println("운전하는 기능");
	}
	public void stop() {
		System.out.println("정지하는 기능");
	}
}

//자식 클래스
class FireEngine extends Car {
	public void getWater() {
		System.out.println("물을 뿌리는 기능");
	}
}

public class InstanceofMain02 {
	public static void main(String[] args) {
		FireEngine fe = new FireEngine();
		
		//객체           사용가능한 타입
		if(fe instanceof FireEngine) {
			System.out.println("This is a FireEngine Instance");
		}
		
		if(fe instanceof Car) {
			System.out.println("This is a Car Instance");
		}
		
		if(fe instanceof Object) {
			System.out.println("This is an Object Instance");
		}
	}
}

