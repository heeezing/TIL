package kr.spring.order.service;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	//주문 등록
	public int selectOrderNum(); //주문번호생성
	public void insertOrder(OrderVO order);
	public void insertOrderDetail(OrderDetailVO vo);
}
