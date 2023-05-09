package kr.s18.bank;

public class BankMain {
	/*
	 * [실습]
	 * 1.일반 계좌 생성
	 * 2.입금하기
	 * 3.출금하기
	 * 4.계좌 정보 출력
	 */
	public static void main(String[] args) {
		//일반 계좌 개설
		BankAccount ba = new BankAccount("012-345", "1234", "김희진", 10000);
		System.out.println("------------------");
		//계좌 정보 출력
		ba.printAccount();
		//입금 및 계좌 정보 출력
		ba.deposit(20000);
		ba.printAccount();
		System.out.println("------------------");
		//출금 및 계좌 정보 출력
		ba.withdraw(25000);
		ba.printAccount();
		System.out.println("==================");
		
		//마이너스 계좌 개설
		MinusAccount ma = new MinusAccount("200-123", "5678", "홍길동", 10000, 500);
		System.out.println("------------------");
		//계좌 정보 출력
		ma.printAccount();
		//입금 및 계좌 정보 출력
		ma.deposit(10000);
		ma.printAccount();
		System.out.println("------------------");
		//출금 및 계좌 정보 출력
		ma.withdraw(20500);
		ma.printAccount();
		
	}
}
