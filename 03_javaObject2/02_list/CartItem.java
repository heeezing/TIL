package kr.s02.list;

public class CartItem {
	//멤버변수
	private String code; //상품코드
	private int num; //수량
	private int price; //단가
	
	//생성자
	public CartItem(String code, int num, int price) {
		this.code = code;
		this.num = num;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
