package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CartVO;

@Service
@Transactional
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void insertCart(CartVO cart) {
		cartMapper.insertCart(cart);
	}

	@Override
	public int selectTotalByMem_num(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartVO> selectListCart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartVO selectCart(CartVO cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCart(CartVO cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCartByItem_num(CartVO cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCart(Integer cart_num) {
		// TODO Auto-generated method stub
		
	}

}
