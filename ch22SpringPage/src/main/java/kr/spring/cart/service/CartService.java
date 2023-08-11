package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import kr.spring.cart.vo.CartVO;

public interface CartService {
	//장바구니에 상품 등록
	public void insertCart(CartVO cart);
	//회원번호(mem_num)별 총 구매 금액
	public int selectTotalByMem_num(Map<String,Object> map);
	//장바구니 목록
	public List<CartVO> selectListCart(Map<String,Object> map);
	//장바구니 상세
	public CartVO selectCart(CartVO cart);
	//장바구니 수정 (개별 상품 수량 수정)
	public void updateCart(CartVO cart);
	//장바구니 상품번호와 회원번호 별 수정
	public void updateCartByItem_num(CartVO cart);
	//장바구니 삭제
	public void deleteCart(Integer cart_num);
}