package kr.s03.operation;

public class BreakMain03 {
	public static void main(String[] args) {
		//break label문
		exit_for: //레이블 설정 (정해져있진X)
		for(int i=0 ; i<3 ; i++) {
			for(int j=0 ; j<5 ; j++ ) {
				if(j==3) {
					//레이블이 지정된 for문 영역을 빠져나감
					break exit_for; 
				}
				System.out.println(i + "," + j);
			}
		}
	}
}
