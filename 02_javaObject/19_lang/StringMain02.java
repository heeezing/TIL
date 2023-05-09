package kr.s19.lang;

public class StringMain02 {
	public static void main(String[] args) {
		String s1 = "Kwon Sun Ae";
		           //01234567890, 길이=11
		int index = s1.indexOf('n'); //글자가 중복될 경우, 맨 앞에서부터 글자를 찾음
		System.out.println("맨 처음 문자 n의 위치 : " + index); //index = 3
		
		index = s1.indexOf("Sun");
		System.out.println("문자열 Sun의 위치 : " + index); //index = 5
		
		index = s1.lastIndexOf('n'); //글자가 중복될 경우, 맨 뒤에서부터 글자를 찾음
		System.out.println("마지막 문자 n의 위치 : " + index); //index = 7
		
		char c = s1.charAt(index); //문자 추출
		System.out.println("추출한 문자 : " + c); //c = n
		
		index = s1.indexOf('S'); //만약 소문자 's'를 입력한다면, 없다는 의미로 index = -1이 됨.
		//전달된 인덱스로부터 마지막 인덱스까지 문자열 추출
		String str = s1.substring(index); // index = 5
		System.out.println("대문자 S부터 끝까지 잘라내기 : " + str); //str = Sun Ae
		
		//begin index부터 end index "전"까지 문자열 추출
		str = s1.substring(index, index+3); //5,8 -> 5부터 7까지 
		System.out.println("대문자 S로부터 3자까지 잘라내기 : " + str); //str = Sun
		
		int length = s1.length(); //문자열의 길이 추출
		System.out.println("s1의 길이 : " + length); //length = 11
		
		String[] array = s1.split(" "); //구분자(여기선 공백)를 이용해서 문자열을 잘라냄 
		                                //반환하는 데이터는 배열에 넣어서 반환	
		for(int i=0 ; i<array.length ; i++) {
			System.out.println("array[" + i + "]:" + array[i]);
		}
		//array[0]:Kwon
		//array[1]:Sun
		//array[2]:Ae
	}
}
