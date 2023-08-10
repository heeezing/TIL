package kr.spring.order.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {
	private int order_num; //주문번호
	private String item_name; //주문상품명
	private int order_total; //총 구매 금액
	@Range(min=1,max=2)
	private int payment; //지불 방식
	private int status; //배송 상태
	@NotEmpty
	private String receive_name; //수령인
	@NotEmpty
	private String receive_post; //배송지 우편번호
	@NotEmpty
	private String receive_address1; //배송지 주소
	@NotEmpty
	private String receive_address2; //배송지 상세주소
	@NotEmpty
	private String receive_phone; //배송지 전화번호
	private String notice; //남기실 말씀
	private Date reg_date; //구매일
	private Date modify_date; //구매일
	private int mem_num; //구매자 회원번호
	private String id; //구매자 아이디
	
	private int[] cart_numbers; //장바구니에서 선택한 상품의 상품번호들
}
