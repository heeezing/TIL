package kr.s19.lang;

public class StringMain05 {
	public static void main(String[] args) {
		String str = "abvMDye-4W?EWzz";
		String result = "";
		                //String->char[]로 변환하는 메서드
		for(char c: str.toCharArray()) { 
			//대문자이면 true, 소문자이면 false를 반환하는 메서드
			if(Character.isUpperCase(c)) { //대문자
				//소문자로 변환
				result += Character.toLowerCase(c);
			}else if(Character.isLowerCase(c)) { //소문자
				//대문자로 변환
				result += Character.toUpperCase(c);
			}else {
				result += c;
			}	
		}		
		System.out.println(result);
	}
}
