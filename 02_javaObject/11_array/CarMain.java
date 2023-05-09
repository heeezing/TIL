package kr.s11.array;

class Car {
	//은닉화
	private String color;
	private int speed;
	
	//캡슐화
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
		}
	public int getSpeed() {
		return speed;
	}
}

public class CarMain {
	public static void main(String[] args) {
		//배열 선언 및 생성
		Car[] carArray = new Car[2];
		
		for(int i=0 ; i<carArray.length ; i++) {
			System.out.println(carArray[i]); //null로 출력 - 데이터가 없다.
		}
		System.out.println("-------------");
		
		//Car 객체를 생성해서 배열의 index 0에 저장 -> Car 객체의 주소가 저장됨
		carArray[0] = new Car(); 
		//배열의 index 0에 저장된 객체의 주소를 이용해서 객체 호출
		carArray[0].setColor("흰색");
		carArray[0].setSpeed(100);
		
		//Car 객체를 생성해서 배열의 index 1에 저장 -> Car 객체의 주소가 저장됨
		carArray[1] = new Car();
		//배열의 index 1에 저장된 객체의 주소를 이용해서 객체 호출
		carArray[1].setColor("검정색");
		carArray[1].setSpeed(200);
		
		//배열의 요소 출력
		for(int i=0 ; i<carArray.length ; i++) {
			System.out.println(carArray[i]); //참조값(주소) 출력
		}
		System.out.println("-------------");
		
		//배열에 저장된 객체의 주소를 이용해서 객체의 멤버 메서드 호출
		for(int i=0 ; i<carArray.length ; i++) {
			System.out.println("carArray[" + i + "]:색상->" + carArray[i].getColor());
			System.out.println("carArray[" + i + "]:스피드->" + carArray[i].getSpeed());
		}
		System.out.println("-------------");
		
		//확장 for문을 이용한 배열의 요소 출력
		for(Car car : carArray) {
			System.out.println("색상:" + car.getColor() + ", 스피드:" + car.getSpeed());
		}
	}
}
