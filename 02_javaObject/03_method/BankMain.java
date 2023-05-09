package kr.s03.method;

import java.util.Scanner;

class Account {
	String account_num;
	String name;
	int balance;
	//예금하기
	public void deposit(int money) {
		if(money<=0) {
			System.out.println("입금액은 0보다 크게 입력하세요!");
		}else {
			balance += money;
			System.out.println("입금이 완료되었습니다.");
		}
	}
	//출금하기
	public void withdraw(int money) {
		if(money<=0) {
			System.out.println("출금액은 0보다 크게 입력하세요!");
		}else {
			balance -= money;
			System.out.println("출금이 완료되었습니다.");
		}
	}
	//계좌정보
	public void printAccount() {
		System.out.println("계좌번호 : " + account_num);
		System.out.println("예금주 : " + name);
		System.out.printf("잔고 : %,d원%n", balance);
	}
}
public class BankMain {
	/*
	 * [실습]
	 * Account
	 * 1)멤버변수:계좌번호(account_num), 예금주(name), 잔고(balance)
	 * 2)멤버메서드:
	 * -예금하기(deposit) : balance에 값 누적 / void형
	 * 예금 처리 후 "입금이 완료되었습니다." 문구 출력
	 * -출금하기(withdraw) : balance의 값 차감
	 * 출금 처리 후 "출금이 완료되었습니다." 문구 출력
	 * -계좌정보 출력(printAccount) : 계좌번호, 예금주, 잔고 출력
	 * 
	 * BankMain main
	 * 1)Account 생성
	 * 2)계좌 기본 정보 입력 : 계좌번호, 예금주, 잔고
	 * 3)while(true)형식으로 반복문 안에 메뉴
	 * (1.예금 | 2.출금 | 3.잔고 확인 | 4.종료)
	 */
	public static void main(String[] args) {		
		Scanner input = new Scanner(System.in);
		
		//객체 선언 및 생성
		//계좌 생성
		Account account = new Account();
		//계좌 기본 정보 입력
		System.out.println("계좌 기본 정보를 입력해서 계좌를 생성합니다.");
		
		System.out.print("계좌번호 : ");
		account.account_num = input.nextLine();
		System.out.print("예금주 : ");
		account.name = input.nextLine();	
		System.out.print("잔고 : ");
		account.balance = input.nextInt();
		
		System.out.println(account.name + "님 계좌가 개설되었습니다.");
		
		while(true) {
			System.out.println("=================================");
			System.out.println("1.예금 | 2.출금 | 3.잔고 확인 | 4.종료");
			System.out.println("=================================");
			System.out.print("메뉴 선택>");
			int num = input.nextInt();
			
			if (num==1) {
				System.out.print("예금액>");
				account.deposit(input.nextInt());
			}else if(num==2) {
				System.out.print("출금액>");
				account.withdraw(input.nextInt());
			}else if(num==3) {
//				System.out.printf("잔고는 %,d원입니다.%n", account.balance);
				account.printAccount();
			}else if(num==4) {
				System.out.println("프로그램 종료");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		input.close();
	}
}
