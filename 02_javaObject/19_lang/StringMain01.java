package kr.s19.lang;

public class StringMain01 {
	public static void main(String[] args) {
		///암시적으로 문자열 생성
		//암시적으로 문자열 생성하면 문자열이 같을 경우 하나의 객체를 만들어서 공유함
		String str1 = "abc";
		String str2 = "abc";
		
		//객체 비교
		if(str1 == str2) { //같은 객체
			System.out.println("str1과 str2는 같은 객체");
		}else { //다른 객체
			System.out.println("str1과 str2는 다른 객체");
		} //str1과 str2는 같은 객체
		
		//문자열 비교 (equals 메서드 이용) -> true면 같다, false면 다르다 출력.
		if(str1.equals(str2)) { //같은 문자열
			System.out.println("str1과 str2의 내용이 같다.");
		}else { //다른 문자열
			System.out.println("str1과 str2의 내용이 다르다.");
		} //str1과 str2의 내용이 같다.
		
		
		///명시적으로 문자열 생성
		//명시적으로 문자열을 생성하면 같은 문자열이라도 객체를 따로 생성함
		String str3 = new String("Hello");
		String str4 = new String("Hello");
		
		//객체 비교
		if(str3 == str4) { //같은 객체
			System.out.println("str3과 str4는 같은 객체");
		}else { //다른 객체
			System.out.println("str3과 str4는 다른 객체");
		} //str3과 str4는 다른 객체
		
		//문자열 비교
		if(str3.equals(str4)) { //같은 문자열
			System.out.println("str3과 str4의 내용이 같다.");
		}else { //다른 문자열
			System.out.println("str3과 str4의 내용이 다르다.");
		} //str3과 str4의 내용이 같다.
		
		System.out.println("-----------------");
		
		String str5 = "bus";
		String str6 = "Bus";
		
		//대소문자를 구분하지 않고 문자열 비교
		if(str5.equalsIgnoreCase(str6)) {
			System.out.println("[대소문자 구분 없이 비교]str5와 str6의 내용이 같다.");
		}else {
			System.out.println("[대소문자 구분 없이 비교]str5와 str6의 내용이 다르다.");
		}
		
	}
}
