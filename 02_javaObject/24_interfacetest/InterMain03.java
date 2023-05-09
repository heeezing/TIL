package kr.s24.interfacetest;

//인터페이스
interface Inter1 {
	public int getA(); //abstract 생략한 추상메서드
}

interface Inter2 {
	public int getB(); //abstract 생략한 추상메서드
}

//인터페이스 간 다중 상속
interface Inter3 extends Inter1, Inter2 {
	public int getData(); //abstract 생략한 추상메서드
}

//인터페이스를 클래스에 구현
public class InterMain03 implements Inter3 {
	//인터페이스의 추상메서드 구현 - 클래스에서 다 해줘야함
	@Override
	public int getA() {
		return 100;
	}
	@Override
	public int getB() {
		return 200;
	}
	@Override
	public int getData() {
		return 300;
	}
	
	public static void main(String[] args) {
		InterMain03 im = new InterMain03();
		System.out.println(im.getA());
		System.out.println(im.getB());
		System.out.println(im.getData());
	}
}
