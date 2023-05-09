package kr.s01.exception;

public class ExceptionMain07 {
	
	public void methodA(String[] n) throws Exception {
		if(n.length > 0) {  //데이터 입력의 경우
			for(String s : n) {
				System.out.println(s);
			}
		}else {  //데이터 미입력의 경우
			throw new Exception("입력한 데이터가 없습니다.");
		}
	}
	
	public static void main(String[] args) {
		ExceptionMain07 ex = new ExceptionMain07();
		try {
			ex.methodA(args);
		}catch(Exception e) {
			System.out.println(e.toString());  //생성된 예외 클래스명이 나옴 (선택 사항)
			                                   //java.lang.Exception: 입력한 데이터가 없습니다.
			System.out.println(e.getMessage());  //생성된 예외 클래스명이 안 나옴 (선택 사항)
		}                                        //입력한 데이터가 없습니다.

	}
}
