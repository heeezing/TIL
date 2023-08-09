package kr.spring.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Override
	public int selectOrderNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertOrder(OrderVO order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOrderDetail(OrderDetailVO vo) {
		// TODO Auto-generated method stub
		
	}

}
