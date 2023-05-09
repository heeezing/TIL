package kr.s10.trainnig;

public class Animal {
	/*
	 * [실습]
	 * Animal
	 * 멤버변수 : 이름(name), 나이(age), 비행여부(fly)
	 * 멤버메서드 : set,get 메서드 생성
	 *            boolean 타입일 경우 - 예)setFly, isFly
	 * 생성자 : 인자가 없는 생성자, 인자가 있는 생성자
	 */
	//멤버변수
	private String name;
	private int age;
	private boolean fly;
	
	///생성자
	//인자가 있는 생성자
	public Animal(String name, int age, boolean fly) { 
	//    멤버변수   지역변수
		this.name = name; //
		this.age = age; //
		this.fly = fly; //
	}
	//인자가 없는 생성자
	public Animal() {} 

	//멤버 메서드
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isFly() {
		return fly;
	}

	public void setFly(boolean fly) {
		this.fly = fly;
	}
	
}
