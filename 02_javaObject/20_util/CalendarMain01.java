package kr.s20.util;

import java.util.Calendar;

public class CalendarMain01 {
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		System.out.println(today);  //엄청 긴 정보가 출력. 이 중에 필요한 정보를 빼내는 식으로 활용.
		
		int year = today.get(Calendar.YEAR); //년
		int month = today.get(Calendar.MONTH) + 1; //월 (0~11로 반환하기 때문에 1 더해줘야함.)
		int date = today.get(Calendar.DATE); //일
		
		System.out.printf("%d년 %d월 %d일 ", year, month, date);
		
		int day = today.get(Calendar.DAY_OF_WEEK); //요일 (1~7로 반환)
		
		String nday = null; //누적이 아니라 대입이므로 null로 했음. ""해도 상관없긴함.
		switch (day) {
		case 1: nday = "일"; break;
		case 2: nday = "월"; break;
		case 3: nday = "화"; break;
		case 4: nday = "수"; break;
		case 5: nday = "목"; break;
		case 6: nday = "금"; break;
		case 7: nday = "토"; break;
		}
		System.out.printf("%s요일", nday);
		
		//오전 0, 오후 1
		int amPm = today.get(Calendar.AM_PM);
		String str = (amPm == Calendar.AM ? "오전" : "오후");
		
		//시 (HOUR : 12시 표시 / HOUR_OF_DAY : 24시 표기)
		int hour = today.get(Calendar.HOUR);
		//분
		int min = today.get(Calendar.MINUTE);
		//초
		int sec = today.get(Calendar.SECOND);
		
		System.out.printf(" %s %d시 %d분 %d초", str, hour, min, sec);
	}
}
