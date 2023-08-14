package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void insertOrder(OrderVO order, List<OrderDetailVO> list) {
		//vo에는 order_num이 없기 때문에 따로 생성해서 넣어줌(호출할 때마다 1씩 증가하는 시퀀스)
		order.setOrder_num(orderMapper.selectOrderNum());
		orderMapper.insertOrder(order);
		for(OrderDetailVO vo : list) {
			vo.setOrder_num(order.getOrder_num()); //생성해서 넣어놓은 걸 받아옴
			orderMapper.insertOrderDetail(vo);
			//재고 수 업데이트
			orderMapper.updateQuantity(vo);
			//주문 완료된 상품을 장바구니에서 제거
			orderMapper.deleteCartItem(vo.getItem_num(), order.getMem_num());
		}
	}

	@Override
	public int selectOrderCount(Map<String, Object> map) {
		return orderMapper.selectOrderCount(map);
	}

	@Override
	public List<OrderVO> selectListOrder(Map<String, Object> map) {
		return orderMapper.selectListOrder(map);
	}

	@Override
	public int selectOrderCountByMem_num(Map<String, Object> map) {
		return orderMapper.selectOrderCountByMem_num(map);
	}

	@Override
	public List<OrderVO> selectListOrderByMem_num(Map<String, Object> map) {
		return orderMapper.selectListOrderByMem_num(map);
	}

	@Override
	public OrderVO selectOrder(Integer order_num) {
		return orderMapper.selectOrder(order_num);
	}

	@Override
	public List<OrderDetailVO> selectListOrderDetail(Integer order_num) {
		return orderMapper.selectListOrderDetail(order_num);
	}

	@Override
	public void updateOrder(OrderVO order) {
		orderMapper.updateOrder(order);
	}

	@Override
	public void updateOrderStatus(OrderVO order) {
		orderMapper.updateOrderStatus(order);
		//주문 취소일 경우만 상품 개수를 조정
		if(order.getStatus() == 5) {
			List<OrderDetailVO> detailList = orderMapper.selectListOrderDetail(order.getMem_num());
			for(OrderDetailVO vo : detailList) {
				orderMapper.updateItemQuantity(vo);
			}
		}
	}

}
