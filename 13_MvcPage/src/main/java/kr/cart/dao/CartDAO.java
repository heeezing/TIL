package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.cart.vo.CartVO;
import kr.util.DBUtil;

public class CartDAO {
	//싱글턴 패턴
	private static CartDAO instance = new CartDAO();
	public static CartDAO getInstance() {
		return instance;
	}
	private CartDAO() {}
	
	
	//장바구니 등록
	public void insertCart(CartVO cart) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO zcart (cart_num,item_num,order_quantity,mem_num) "
				+ "VALUES (zcart_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getItem_num());
			pstmt.setInt(2, cart.getOrder_quantity());
			pstmt.setInt(3, cart.getMem_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, null);
		}
	}
	
	
	//회원번호(mem_num)별 총 구매액
	//장바구니 목록
	//장바구니 상세
	//장바구니 수정(개별 상품 수량 수정)
	//장바구니 상품번호와 회원번호별 수정
	//장바구니 삭제
}
