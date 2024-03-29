package kr.spring.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Mapper
public interface OrderMapper {
	//[주문 등록]
	//주문번호생성
	@Select("SELECT sporder_seq.nextval FROM dual")
	public int selectOrderNum();
	//등록 - 주문에 대한 일반적인 사항
	public void insertOrder(OrderVO order);
	//등록 - 주문한 상품에 대한 사항
	public void insertOrderDetail(OrderDetailVO vo);
	//재고 수 업데이트
	@Update("UPDATE spitem SET quantity=quantity-#{order_quantity} WHERE item_num=#{item_num}")
	public void updateQuantity(OrderDetailVO orderDetailVO);
	//주문 완료된 상품을 장바구니에서 제거
	@Delete("DELETE FROM spcart WHERE item_num=#{item_num} AND mem_num=#{mem_num}")
	public void deleteCartItem(@Param(value="item_num") Integer item_num,
						 	   @Param(value="mem_num") Integer mem_num);
	//[관리자] 전체or검색 레코드 수
	public int selectOrderCount(Map<String,Object> map);
	//[관리자] 전체or검색 목록
	public List<OrderVO> selectListOrder(Map<String,Object> map);
	//[사용자] 전체or검색 레코드 수
	public int selectOrderCountByMem_num(Map<String,Object> map);
	//[사용자] 전체or검색 목록
	public List<OrderVO> selectListOrderByMem_num(Map<String,Object> map);
	//[관리자&사용자] 주문 상세
	@Select("SELECT * FROM sporder WHERE order_num=#{order_num}")
	public OrderVO selectOrder(Integer order_num);
	//[관리자&사용자] 개별 상품 목록
	@Select("SELECT * FROM sporder_detail WHERE order_num=#{order_num} ORDER BY item_num DESC")
	public List<OrderDetailVO> selectListOrderDetail(Integer order_num);
	//[관리자&사용자] 배송지 수정
	public void updateOrder(OrderVO order);
	//[관리자&사용자] 주문 상태 수정
	@Update("UPDATE sporder SET status=#{status},modify_date=SYSDATE WHERE order_num=#{order_num}")
	public void updateOrderStatus(OrderVO order);
	//[관리자&사용자] 주문 취소 시 상품 수량 업데이트
	@Update("UPDATE spitem SET quantity = quantity + #{order_quantity} WHERE item_num=#{item_num}")
	public void updateItemQuantity(OrderDetailVO orderDetailVO);
}
