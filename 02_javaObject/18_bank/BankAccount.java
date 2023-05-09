package kr.s18.bank;

public class BankAccount {
	/*
	 * [실습]
	 * -기본 계좌
	 * 멤버변수 : 계좌번호(number), 비밀번호(password),
	 *           예금주(name), 잔고(balance)
	 * 생성자 : 인자가 있는 생성자
	 *         출력 - "010-123 계좌가 개설되었습니다."
	 * 멤버메서드 :
	 * 입금(deposit) - 0 이하의 금액은 입금할 수 없습니다.
	 *              - "1000원이 입금되었습니다."
	 * 출금(withdraw) - 0 이하의 금액은 출금할 수 없습니다.
	 *              - "1000원이 출금되었습니다."
	 * 계좌정보출력(printAccount) - (일반)계좌번호, 비밀번호, 예금주, 잔고
	 */
	protected String number;
	protected String password;
	protected String name;
	protected int balance;
	
	public BankAccount (String number, String password, String name, int balance) {
		this.number = number;
		this.password = password;
		this.name = name;
		this.balance = balance;
		System.out.println(number + " 계좌가 개설되었습니다.");
	}

	public void deposit(int money) {
		if (money <= 0) {
			System.out.println("0 이하의 금액은 입금할 수 없습니다.");
			return;
		}
		balance += money;
		System.out.printf("%,d원이 입금되었습니다.%n", money);
	}		
	
	public void withdraw(int money) {
		if (money <= 0) {
			System.out.println("0 이하의 금액은 출금할 수 없습니다.");
			return;
		}
		if (balance < money) {
			System.out.println("잔고가 부족합니다.");
			return;
		}
		balance -= money;
		System.out.printf("%,d원이 출금되었습니다.%n", money);
	}
	
	public void printAccount() {
		System.out.printf("(일반)계좌번호 : %s%n", number);
		System.out.printf("비밀번호 : %s%n", password);
		System.out.printf("예금주 : %s%n", name);
		System.out.printf("잔고: %,d원%n", balance);
	}
}
