package kr.s12.extention;

//부모 클래스
class A {
	int x = 100;
	private int y = 200; //은닉화
	//private이라는 접근 제한에 걸렸기 때문에 public 거쳐야.
	//protected의 경우 상속 관계 허용
	public int getY() { 
		return y;
	}
}
//자식 클래스에 부모 클래스 상속
class B extends A{
	int z = 300;
}

public class ExtentionMain03 {
	public static void main(String[] args) {
		B bp = new B();
		System.out.println(bp.x);
		//private 멤버 변수는 같은 클래스 내에서만 호출
		//System.out.println(bp.y); -> 오류
		System.out.println(bp.getY());
		System.out.println(bp.z);
	}
}
