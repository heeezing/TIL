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
}
