package kr.s03.method;

public class MethodArgMain03 {
	//인자 전달 방식 : Variable Arguments (가변 인자)
	//자료형이 일치할 때 전달하고자 하는 값의 개수를 다르게 지정할 수 있다.
	//전달 되는 데이터는 내부적으로 배열로 인식함
	public void argTest(int ... n) {
		for(int i=0 ; i<n.length ; i++) {
			System.out.println("n[" + i + "]:" + n[i]);
		}
		System.out.println("-----------");
	}
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		MethodArgMain03 ma = new MethodArgMain03();		
		ma.argTest(); //데이터가 없음 -> n.length가 0이기 때문에 for문 실행x. 구분선 출력문만 실행.
		ma.argTest(10);
		ma.argTest(10,20);
		ma.argTest(10,20,30);
	
	}
}
