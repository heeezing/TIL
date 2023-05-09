package kr.s09.thistest;

class ThisTest {
	//은닉화
	private int a;
	
	//캡슐화 메서드
	public void setA(int a) {
	//  멤버변수  지역변수
		this.a = a;
	}
	
	public int getA() {
		return a;
	}
}

public class ThisMain03 {
	public static void main(String[] args) {
		ThisTest tt = new ThisTest();
		tt.setA(10);
		System.out.println(tt.getA()); //10
	}
}
