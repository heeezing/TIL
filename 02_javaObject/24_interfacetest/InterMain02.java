package kr.s24.interfacetest;

//인터페이스 
interface A2 {
	//추상 메서드 (원형)
	public abstract void make();
	//추상 메서드 (public abstract 생략 가능)
	void play();
}

//클래스
class B2 implements A2 {
	//인터페이스의 추상메서드를 구현
	@Override
	public void make() {
		System.out.println("make메서드");
	}
	@Override
	public void play() {
		System.out.println("play메서드");
	}
}

public class InterMain02 {
	public static void main(String[] args) {
		B2 bp = new B2();
		bp.make();
		bp.play();
	}
}
