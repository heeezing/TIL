package kr.spring.order.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Mapper
public interface OrderMapper {
	//주문 등록
	public int selectOrderNum(); //주문번호생성
	public void insertOrder(OrderVO order);
	public void insertOrderDetail(OrderDetailVO vo);
	
}
