package kr.s09.thistest;

public class ThisMain04 {
	/*
	 * 생성자 내부에서 또 다른 생성자를 호출할 때 this()를 표시함.
	 * this()를 이용해서 또 다른 생성자를 호출하는 경우는
	 * 생성자 내부에 반복적인 코드가 있어서
	 * 반복적인 코드를 제거하고 코드를 재사용하고자 할 때 this()를 명시함.
	 */
	public ThisMain04() {
		//this() 앞에 일반 수행문을 명시하면 오류 발생
		//System.out.println("~~~~~"); ->오류. this()는 맨 위에서 초기화 작업을 한다.
		this("바람");
		System.out.println("*****");
	}
	public ThisMain04(String msg) {
		System.out.println(msg);
	}
	public ThisMain04(int a) {
		this(String.valueOf(a));
	}
	
	public static void main(String[] args) {
		ThisMain04 tm1 = new ThisMain04();
		ThisMain04 tm2 = new ThisMain04("여름");
		ThisMain04 tm3 = new ThisMain04(5000);
		
		
	}
}
