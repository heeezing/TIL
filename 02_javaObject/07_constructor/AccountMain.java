package kr.s07.constructor;

public class AccountMain {
	//멤버 변수
	String accountNo; //계좌번호
	String ownerName; //예금주
	int balance; // 잔고
	
	//인자가 있는 생성자
	public AccountMain(String a, String o, int b) {
		accountNo = a;
		ownerName = o;
		balance = b;
	}
	//멤버 메서드
	//입금 기능
	public void deposit(int amount) {
		if(amount <= 0) {
			System.out.println("0보다 크게 입력해야 합니다.");
			return; //void형에서 return을 만나면 메서드를 빠져나오게 된다.
		}
		//누적
		balance += amount;
		System.out.println("입금이 완료되었습니다.");
	}
	//출금 기능
	public void withdraw(int amount) {
		if(amount <= 0) {
			System.out.println("0보다 크게 입력해야 합니다.");
			return;
		}
		if(balance < amount) {
			System.out.println("잔고가 부족합니다.");
			return;
		}
		//차감
		balance -= amount;
		System.out.println("출금이 완료되었습니다.");
	}
	//계좌 정보 출력 기능
	public void printAccount() {
		System.out.println("계좌번호:" + accountNo);
		System.out.println("예금주:" + ownerName);
		System.out.printf("잔고:%,d원%n", balance);
		System.out.println("-----------");
	}
	
	public static void main(String[] args) {
		//인자가 있는 생성자를 이용해서 객체 생성
		AccountMain am1 = new AccountMain("123-123","홍길동",10000);
		//계좌 정보 호출
		am1.printAccount();
		//입금하기
		am1.deposit(20000);
		am1.printAccount(); //입금 잘 됐는지 확인용
		//출금하기
		am1.withdraw(15000);
		am1.printAccount();
		
		AccountMain am2 = new AccountMain("567-567","박문수",20000);
		//계좌 정보 호출
		am2.printAccount();
	}
}
