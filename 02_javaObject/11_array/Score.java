package kr.s11.array;

public class Score {
	/*
	 * [실습]
	 * 멤버변수 : 이름(name), 국어(korean), 영어(english), 수학(math)
	 * 생성자 : 인자가 없는 생성, 인자가 있는 생성
	 * 멤버메서드 : get/set 메서드 정의,
	 *           총점(makeSum()), 평균(makeAvg()), 등급(makeGrade())
	 *
	 */
	
	private String name;
	private int korean;
	private int english;
	private int math;
	
	public Score() {}
	public Score(String name, int korean, int english, int math) {
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int makeSum() {
		return korean + english + math;
	}
	
	public int makeAvg() {
		return makeSum() / 3 ;
	}
	
	public String makeGrade() {
		String grade;
		switch(makeAvg()/10) {
			case 10: case 9: grade = "A"; break;
			case 8 : grade ="B"; break;
			case 7 : grade ="C"; break;
			case 6 : grade ="D"; break;
			default : grade = "F"; 
		}
		return grade;
	}
}
