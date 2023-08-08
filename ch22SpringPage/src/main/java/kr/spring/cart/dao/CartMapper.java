package kr.spring.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.cart.vo.CartVO;

@Mapper
public interface CartMapper {
	//장바구니에 상품 등록
	public void insertCart(CartVO cart);
	//회원번호(mem_num)별 총 구매 금액
	public int selectTotalByMem_num(Map<String,Object> map);
	//장바구니 목록
	public List<CartVO> selectListCart(Map<String,Object> map);
	//장바구니 상세
	@Select("SELECT * FROM spcart WHERE item_num=#{item_num} AND mem_num=#{mem_num}")
	public CartVO selectCart(CartVO cart);
	//장바구니 수정 (개별 상품 수량 수정)
	@Update("UPDATE spcart SET order_quantity=#{order_quantity} WHERE cart_num=#{cart_num}")
	public void updateCart(CartVO cart);
	//장바구니 상품번호와 회원번호 별 수정
	@Update("UPDATE spcart SET order_quantity=#{order_quantity} "
		  + "WHERE item_num=#{item_num} AND mem_num=#{mem_num}")
	public void updateCartByItem_num(CartVO cart);
	//장바구니 삭제
	@Delete("DELETE FROM spcart WHERE cart_num=#{cart_num}")
	public void deleteCart(Integer cart_num);
	//상품 삭제 시 장바구니에 담겨있는 상품 삭제
	public void deleteCartByItem_num(Integer item_num);
}
