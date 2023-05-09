package kr.s06.capsule;

class Capsule { 
	//은닉화
	private int a; //만약 public이나 default였다면 main에서 호출 가능
	
	//캡슐화 (메서드가 a를 감싸주고 있음)
	///데이터를 셋팅하는 메서드
	public void setA(int n) {
		if(n>=0 ) {
			a = n;
		}else {
			System.out.println("음수는 허용되지 않습니다.");
		}
	}
	///데이터를 반환하는 메서드
	public int getA() {
		return a;
	}
}

public class CapsuleMain {
	public static void main(String[] args) {
		Capsule cap = new Capsule();
		/*
		 * 변수 a의 접근 지정자(제한자)가 private이기 때문에
		 * 같은 클래스 내에서는 호출이 가능하지만
		 * 다른 클래스에서는 호출이 불가능하다.
		 */
		//cap.a = -1; ->오류
		//System.out.println(cap.a); ->오류
		
		cap.setA(100); //데이터 저장
		System.out.println(cap.getA()); //데이터 호출
		System.out.println("---------");
		
		cap.setA(-200);
		System.out.println(cap.getA()); 
	}
}
