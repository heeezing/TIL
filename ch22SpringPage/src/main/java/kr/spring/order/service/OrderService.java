package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	//주문 등록
	public void insertOrder(OrderVO order, List<OrderDetailVO> list);
	//[관리자] 전체or검색 레코드 수
	public int selectOrderCount(Map<String,Object> map);
	//[관리자] 전체or검색 목록
	public List<OrderVO> selectListOrder(Map<String,Object> map);
	//[사용자] 전체or검색 레코드 수
	public int selectOrderCountByMem_num(Map<String,Object> map);
	//[사용자] 전체or검색 목록
	public List<OrderVO> selectListOrderByMem_num(Map<String,Object> map);
	//주문 상세
	public OrderVO selectOrder(Integer order_num);
	//개별 상품 목록
	public List<OrderDetailVO> selectListOrderDetail(Integer order_num);
	//[관리자&사용자] 배송지 수정
	public void updateOrder(OrderVO order);
	//[관리자&사용자] 주문 상태 수정
	public void updateOrderStatus(OrderVO order);
}
