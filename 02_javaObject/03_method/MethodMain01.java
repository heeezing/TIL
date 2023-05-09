package kr.s03.method;

public class MethodMain01 { 
	//인자에 데이터를 전달하고 메서드 내에서 가공한 후 결과값을 반환하는 메서드
    //  반환타입(반환형)
	public int add(int a, int b) {
		return a + b;
		}
	
	//반환하는 데이터가 없는 경우
	//    =비어있는
	public void print(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		MethodMain01 method = new MethodMain01();
		//반환하는 데이터가 있는 메서드 호출
		int result = method.add(100, 200); //100+200=300
		System.out.println("result = " + result);
		//반환하는 데이터가 없는 메서드 호출
		method.print("서울");
		
	}
}
