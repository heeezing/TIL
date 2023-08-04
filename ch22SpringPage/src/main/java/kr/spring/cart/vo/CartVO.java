package kr.spring.cart.vo;

import java.sql.Date;

import kr.spring.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {
	private int cart_num; //장바구니 번호
	private int item_num; //상품 번호
	private int order_quantity; //주문 상품 수
	private Date reg_date; //등록일
	private int mem_num; //회원 번호
	
	private int sub_total; //동일 상품의 합계 금액
	private ItemVO itemVO;
}
