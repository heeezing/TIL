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
		return cartMapper.selectTotalByMem_num(map);
	}

	@Override
	public List<CartVO> selectListCart(Map<String, Object> map) {
		return cartMapper.selectListCart(map);
	}

	@Override
	public CartVO selectCart(CartVO cart) {
		return cartMapper.selectCart(cart);
	}

	@Override
	public void updateCart(CartVO cart) {
		cartMapper.updateCart(cart);
	}

	@Override
	public void updateCartByItem_num(CartVO cart) {
		cartMapper.updateCartByItem_num(cart);
	}

	@Override
	public void deleteCart(Integer cart_num) {
		cartMapper.deleteCart(cart_num);
	}

}
