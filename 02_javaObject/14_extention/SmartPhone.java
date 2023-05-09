package kr.s14.extention;

//자식 클래스에 부모 클래스를 상속
public class SmartPhone extends Phone{
	private String os;
	
	//인자가 있는 생성자
	public SmartPhone(String number, String model, String color, String os) {
		this.number = number;
		this.model = model;
		this.color = color;
		this.os = os;	
	}
	
	public String getOs() {
		return os;
	}
}
