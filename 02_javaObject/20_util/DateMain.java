package kr.s20.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateMain {
	public static void main(String[] args) {
		Date now = new Date();

		System.out.println(now); //Wed Mar 29 15:05:58 KST 2023
		//원래는 참조변수를 출력하면 toString이 자동 적용되는데,
		//이 경우 Object의 toString을 불러와 Date에서 재정의한 것.
		System.out.println(now.toLocaleString());  //2023. 3. 29. 오후 3:09:32
		//하지만 deprecated되어 사용하지 않음.
		System.out.println("-----------------");
		
		DateFormat df = DateFormat.getInstance();
		//new를 통해 객체를 생성하지 않고 getInstance()메서드를 이용해서 객체를 생성해아함.
		String today = df.format(now);
		System.out.println(today);  //23. 3. 29. 오후 3:13
		System.out.println("-----------------");
		
		df = DateFormat.getDateTimeInstance();
		today = df.format(now);
		System.out.println(today);  //2023. 3. 29. 오후 3:21:25
		System.out.println("-----------------");
		
		df = DateFormat.getDateInstance();
		today = df.format(now);
		System.out.println(today);  //2023. 3. 29.
		System.out.println("-----------------");
		
		df = DateFormat.getTimeInstance();
		today = df.format(now);
		System.out.println(today);  //오후 3:25:05
		System.out.println("-----------------");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일(E) a hh:mm:ss");
		today = sf.format(now);
		System.out.println(today);  //2023년 03월 29일(수) 오후 03:38:32		
	}
}
