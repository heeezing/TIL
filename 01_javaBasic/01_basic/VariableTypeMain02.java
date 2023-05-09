package kr.s01.basic;
public class VariableTypeMain02 {
	public static void main(String[] args) {
	
	//확장 특수 출력 문자(escape sequence)
		char single = '\'';
		System.out.println(single);
		
		// "를 문자로 쓰고 싶을 때 앞에 \를 붙여줌
		String str = "오늘은 \"목요일\" 입니다.";
		System.out.println(str);
		
		// 문자열에 '를 표시하면 자동으로 일반문자로 변환
		String str2 = "오늘은 '부산'에 비가 와요";
		System.out.println(str2);
		
		// 링크 복사 붙여넣기 하자 자동으로 \앞에 \를 붙여줬음
		String str3 = "C:\\Program Files\\Java\\jdk-11.0.16.1";
		System.out.println(str3);
		
		// \n : 줄바꿈(엔터)
		String str4 = "오늘은 목요일\n내일은 금요일";
		System.out.println(str4);
		
		// \t : 일정 간격 띄어줌
		String str5 = "이름\t나이\t취미";
		System.out.println(str5);
	}
}

