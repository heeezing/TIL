package kr.s13.packtwo;

import kr.s13.packone.People;

public class AccessMain01 {
	public static void main(String[] args) {
		People p = new People();
		///private -> 같은 클래스가 아님. 오류.
		//System.out.println(p.a);

		///default -> 같은 패키지가 아님. 오류.
		//System.out.println(p.b);
		
		///protected -> 같은 패키지도, 상속 관계도 아님. 오류.
		//System.out.println(p.c);
		
		///public -> 접근 제한이 없음. 호출 가능.
		System.out.println(p.d);
	}
}
