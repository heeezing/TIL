package kr.s20.util;

import java.util.StringTokenizer;

public class StringTokenizerMain02 {
	public static void main(String[] args) {
		String source = "2023-03-29 17:37:30";
		
		//여러개의 구분자를 한 번에 지정 가능 - 연속적으로 입력 (split은 하나만 가능)
		StringTokenizer st = new StringTokenizer(source, "- :"); 
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
