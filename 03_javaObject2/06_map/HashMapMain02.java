package kr.s06.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapMain02 {
	public static void main(String[] args) {
		String[] msg = {"Berlin", "Paris", "Seoul", "New York", "London"};
		
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		
		for(int i=0 ; i<msg.length ; i++) {
			     //key value
			map.put(i, msg[i]);
		}
		//HashMap에 key, value로 저장된 데이터의 목록
		System.out.println(map);
		System.out.println("-----------");
		
		//Set<Integer> s = map.keySet();
		//Iterator<Integer> keys = s.iterator();
		Iterator<Integer> keys = map.keySet().iterator();
		
		while(keys.hasNext()) {
			Integer key = keys.next(); //key반환
			System.out.println(key + "," + map.get(key));
		}
	}
}
