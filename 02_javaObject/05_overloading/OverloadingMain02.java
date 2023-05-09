package kr.s05.overloading;

public class OverloadingMain02 {
	//전달되는 인자의 타입을 모두 String으로 변환해서
	//문자열의 길이를 구하는 메서드를 만들기
	public void getLength(int n) {
		String s = String.valueOf(n); //int -> String 변환되는 메서드
		getLength(s); //메서드 안에서 또 다른 메서드를 출력할 수 있다. 15행 메서드 호출
	}
	
	public void getLength(float a) {
		String s = String.valueOf(a); //float -> String 변환되는 메서드
		getLength(s);
	}
	public void getLength(String s) {
		System.out.println(s + "의 길이 : " + s.length());
	}	

	public static void main(String[] args) {
		
		OverloadingMain02 om = new OverloadingMain02();
		om.getLength(1000); //1000 -> "1000" //"1000"의 길이 : 4
		om.getLength(3.14f); //3.14f -> "3.14" //"3.14"의 길이 : 4
		om.getLength("Welcome");
	}
}
