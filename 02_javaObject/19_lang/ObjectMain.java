package kr.s19.lang;

public class ObjectMain {
	public static void main(String[] args) {
		//ObjectMain에 Object가 상속되어 있고
		//Object로부터 상속받은 메서드를 호출
		ObjectMain om = new ObjectMain();
		//클래스 정보 반환
		System.out.println(om.getClass()); //class kr.s19.lang.ObjectMain
		//클래스명 반환
		System.out.println(om.getClass().getName()); //kr.s19.lang.ObjectMain
		//해시코드(10진수) 형태 반환  //해시코드 : 유니크한 식별값
		System.out.println(om.hashCode()); //2104457164
		//해시코드(16진수) 형태 반환
		System.out.println(Integer.toHexString(om.hashCode())); //7d6f77cc
		//참조값 반환
		System.out.println(om); //kr.s19.lang.ObjectMain@7d6f77cc
		System.out.println(om.toString()); //kr.s19.lang.ObjectMain@7d6f77cc
		//toString() -> getClass().getName() + '@' + Integer.toHexString(hashCode())
	}
}
