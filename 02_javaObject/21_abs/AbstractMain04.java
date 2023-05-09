package kr.s21.abs;

//추상 클래스
abstract class AbsEx1 {
	int a = 100;
	int b= 200;
	public String getStr() {
		return "바다";
	}
	//추상 메서드
	public abstract int getA();
	public abstract int getB();
}

//추상 클래스
abstract class AbsEx2 extends AbsEx1 {
	//부모 클래스의 추상 메서드 구현 (선택사항)
	@Override
	public int getA() {
		return a;
	}
	//추상 메서드
	public abstract String getMsg();
}

// 일반 클래스
public class AbstractMain04 extends AbsEx2{
		//부모 클래스의 추상 메서드 구현 (의무사항)
		@Override
		public int getB() {
			return b;
		}
		public String getMsg() {
			return "여름";
		}
	public static void main(String[] args) {
		AbstractMain04 ab = new AbstractMain04();
		System.out.println(ab.getA());
		System.out.println(ab.getB());
		System.out.println(ab.getMsg());
	}
}
