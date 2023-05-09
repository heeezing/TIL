package kr.s04.array;

public class ArrayMain06 {
	public static void main(String[] args) {
		//문자열 배열 선언, 생성
		String[] array = new String[3];
		
		//배열 요소 삽입
		array[0] = "Java";
		array[1] = "Array";
		array[2] = "HTML";
		
		//반복문을 이용한 배열의 요소 출력
		for(int i=0 ; i<array.length ; i++) {
			System.out.println(array[i]);
		}
			System.out.println("--------");
		
		//확장 for문을 이용한 배열의 요소 출력
		for(String str : array) {
			System.out.println(str);
		}
		
		//문자열 배열 선언, 생성(명시적), 초기화
		String[] array2 = new String[] {"서울", "부산"};
		
		//문자열 배열 선언, 생성(암시적), 초기화
		String[] array3 = {"한국", "미국", "영국"};
	}
}