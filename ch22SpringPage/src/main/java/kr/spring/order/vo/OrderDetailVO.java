package kr.spring.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVO {
	private int detail_num; //주문상세번호
	private int item_num; //상품번호
	private String item_name; //상품명
	private int item_price; //가격
	private int item_delivery; //배송비
	private int item_total; //상품구매금액
	private int order_quantity; //상품구매수량
	private int order_num; //
}
