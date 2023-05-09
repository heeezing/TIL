package kr.s10.trainnig;

import java.util.Scanner;

class Coffee {
	int amount; //투입 금액
	int change = 1000; //거스름돈
	
	int coffee = 10; //초기 커피 양
	int cream = 10; //초기 프림 양
	int sugar = 10; //초기 설탕 양
}

public class CoffeeMain {
	/*
	 * [실습]
	 * 메뉴 -> 1.커피 마시기 2.커피 정보 보기 3.종료
	 * 
	 * 1) 커피 마시기
	 * [입력 예시]
	 * 동전을 넣으세요(1잔에 300원):
	 * 구매 수량 입력:
	 * 
	 * (조건체크)
	 * 입력하는 동전과 구매 수량은 0 이하X
	 * 거스름돈이 부족한지 여부 체크
	 * 커피가 부족한지 여부 체크
	 * 프림이 부족한지 여부 체크
	 * 설탕이 부족한지 여부 체크
	 * 
	 * (연산)
	 * 커피 차감(5)
	 * 프림 차감(3)
	 * 설탕 차감(1)
	 * 거스름돈 차감
	 * 투입한 금액 누적
	 * 
	 * [출력 예시]
	 * 거스름 돈 : **
	 * 맛 좋은 커피가 2잔 준비되었습니다.
	 * 
	 * 2)커피 정보 보기
	 * 커피 양 : *
	 * 프림 양 : *
	 * 설탕 양 : *
	 * 거스름 돈 보유 금액 : **원
	 * 누적 금액 : **원
	 */
	public static void main(String[] args) {
		Coffee cf = new Coffee();
		Scanner input = new Scanner(System.in);
	
		while(true) {
			System.out.println("-------------------------------");
			System.out.println("1.커피 마시기 2.커피 정보 보기 3.종료");
			System.out.println("-------------------------------");
			System.out.print("메뉴 선택>");
			int n = input.nextInt();
			
			if(n==1) {
				System.out.println("-------------------------------");
				System.out.print("동전을 넣으세요(1잔 300원):");
				int coin = input.nextInt();
				System.out.print("구매 수량 입력:");
				int count = input.nextInt();
				///조건 체크
				//0이하 숫자 입력
				if(coin <= 0 || count <= 0) {
					System.out.println("0이하의 숫자는 입력 불가");
					continue;
				}
				//투입 동전이 부족함
				if(coin < count*300) {
					System.out.println("동전이 부족합니다.");
					continue;
				}
				//거스름돈이 부족함
				if(coin - count*300 > cf.change) {
					System.out.println("거스름돈이 부족합니다.");
					continue;
				}
				//커피가 부족함
				if(cf.coffee < count*5) {
					System.out.println("커피가 부족합니다.");
					continue;
				}
				//프림이 부족함
				if(cf.cream < count*3) {
					System.out.println("프림이 부족합니다.");
					continue;
				}
				//설탕이 부족함
				if(cf.sugar < count*1) {
					System.out.println("설탕이 부족합니다.");
					continue;
				}
				cf.coffee -= count*5;
				cf.cream -= count*3;
				cf.sugar -= count*1;
				cf.change -= (coin - count*300);
				cf.amount += coin;
				
				System.out.println("거스름 돈 : " + (coin - count*300) + "원");
				System.out.printf("맛 좋은 커피가 " + count + "잔 준비되었습니다");
			}else if(n==2) {
				System.out.println("-------------------------------");
				System.out.println("커피 양 : " + cf.coffee);
				System.out.println("프림 양 : " + cf.cream);
				System.out.println("설탕 양 : " + cf.sugar);
				System.out.printf("거스름 돈 보유 금액 : %,d원%n", cf.change);
				System.out.printf("누적 금액 : %,d원%n", cf.amount);
			}else if(n==3) {
				System.out.println("자판기 종료");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		input.close();
	}
}
