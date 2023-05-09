package kr.s06.map;

import java.util.HashMap;

public class HashMapMain01 {
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		//       key  value
		map.put("김신", 95);
		map.put("지은탁", 100);
		map.put("저승사자", 85);
		map.put("써니", 93);
		map.put("유덕화", 70);
		//key는 중복해서 저장 가능하지만 마지막 value만 인정됨
		map.put("지은탁", 0);
		//value에 null 인정
		map.put("홍길동", null);
		//key에 null 인정
		map.put(null, 100);
		
		//HashMap의 key, value의 쌍의 저장된 정보의 목록
		System.out.println(map);
		System.out.println("----------------");
		
		Integer num = map.get("지은탁");
		System.out.println("지은탁의 성적은 " + num); //지은탁의 성적은 0
                                                  //마지막 입력한 데이터를 사용
	}
}
