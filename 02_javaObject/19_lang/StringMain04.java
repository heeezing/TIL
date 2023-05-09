package kr.s19.lang;

public class StringMain04 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 아래 문자열의 소문자는 대문자로, 대문자는 소문자로 변경하시오.
		 */
		String str = "abcMDye-4W?EWzz";	
		//            012345678901234 = 15
		String result = "";
		
		for(int i=0 ; i<str.length() ; i++) {
			char c = str.charAt(i);
			if(65 <= c && c <= 90) { //대문자
				result += String.valueOf(c).toLowerCase();
			}else if (97 <= c && c <= 122) { //소문자
				result += String.valueOf(c).toUpperCase();
			}else { //대소문자가 아닌 문자
				result += c;
			}
		}
		System.out.println(result);
	}
}
