package kr.s23.enumtest;

public class EnumMain01 {
	//문자열 상수
	public static final String JAVA = "JAVA";
	public static final String ORACLE = "ORACLE";
	public static final String HTML = "HTML";
	
	public static void main(String[] args) {
		System.out.println(JAVA); //같은 클래스에 있기 때문에 클래스명 생략 가능
		System.out.println(ORACLE);
		System.out.println(HTML);
	}
}
