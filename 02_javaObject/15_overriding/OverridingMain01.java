package kr.s15.overriding;

//부모 클래스
class GrandParent {
	public String getCar() {
		return "구형 자동차";
	}
}
//자식 클래스 1
class Father extends GrandParent {
	/*
	 * Method Overriding(메서드 재정의)
	 * 상속 관계일 때 부모 클래스의 메서드를 자식 클래스에서 재정의하는 것.
	 */
	/*
	 * @Override 어노테이션은 메서드 재정의 문법에 맞게 재정의가 되었는지 검증,
	 * 문법에 맞게 재정의 되자 않으면 컴파일 오류가 발생.
	 */
	
	@Override
	public String getCar() {
		return "신형 자동차";
	}
}
//자식 클래스 2
class Uncle extends GrandParent {
	//GrandParent의 getCar() 사용
}

public class OverridingMain01 {
	public static void main(String[] args) {
		Father ft = new Father();
		System.out.println(ft.getCar()); //신형 자동차
		System.out.println("---------");
		
		Uncle uc = new Uncle();
		System.out.println(uc.getCar()); //구형 자동차
	}
}
