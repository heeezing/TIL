package kr.s19.lang;

public class StringMain03 {
	public static void main(String[] args) {
		String s1 = " aBa ";
		String s2 = "abc";
		int a = 100;
		String msg = null;
		
		//대문자로 변환
		msg = s1.toUpperCase();
		System.out.println("msg:" + msg); //msg: ABA 
		
		//소문자로 변환
		msg = s1.toLowerCase();
		System.out.println("msg:" + msg); //msg: aba 
		
		//old문자를 new문자로 대체 (원래 있던 문자 -> 새 문자)
		msg = s1.replace("aB", "b");
		System.out.println("msg:" + msg); //msg: ba 
		
		//문자열 앞뒤 공백 제거
		msg = s1.trim();
		System.out.println("msg:" + msg); //msg:aBa
		
		//대상 문자열에 검색하는 문자열이 포함되어있는지 검증
		boolean f = s1.contains("aB");
		System.out.println("f:" + f); //f:true
		
		//검색하는 문자열이 대상 문자열의 맨 앞에 위치하는지 검증
		f = s2.startsWith("ab");
		System.out.println("f:" + f); //f:true
		
		//검색하는 문자열이 대상 문자열의 맨 뒤에 위치하는지 검증
		f = s2.endsWith("bc");
		System.out.println("f:" + f); //f:true
		
		//int -> String 변환
		msg = String.valueOf(a);
		msg = a + ""; //빈 문자열을 더해줌으로서 새로운 문자열이 됨. " "공백 주의.
	}
}
