package kr.s18.bank;

public class MinusAccount extends BankAccount {
	/*
	 * [실습]
	 * BankAccount를 상속 받아서
	 * 마이너스 계좌의 기능을 갖는 클래스를 정의하시오.
	 * 
	 * 멤버변수 : 한도(minusLimit)
	 * 멤버메서드 : 출금하기(withdraw) - 재정의 / 한도 더해서
	 *           계좌정보(printAccount) - 재정의 / 계좌번호, 잔고
	 */
	private int minusLimit;
	
	public MinusAccount (String number, String password, String name, int balance, int minusLimit) {
		super(number, password, name, balance);
		this.minusLimit = minusLimit;
	}

	@Override
	public void withdraw(int money) {
		if (money <= 0) {
			System.out.println("0 이하의 금액은 출금할 수 없습니다.");
			return;
		}
		if (money > balance + minusLimit) {
			System.out.println("한도를 초과로 출금되지 않습니다.");
			return;
		}
		balance -= money;
		System.out.printf("%,d원이 출금되었습니다.%n", money);
	}
	
	@Override
	public void printAccount() {
		System.out.printf("(마이너스)계좌번호 : %s%n", number);
		System.out.printf("비밀번호 : %s%n", password);
		System.out.printf("예금주 : %s%n", name);
		System.out.printf("잔고: %,d원%n", balance);
		System.out.printf("마이너스 한도: %,d원%n", minusLimit);
	}
}
