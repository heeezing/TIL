package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cart.vo.CartVO;
import kr.item.vo.ItemVO;
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
	public int getTotalByMem_num(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int total = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT SUM(sub_total) "
				+ "FROM (SELECT c.mem_num, c.order_quantity * i.price as sub_total "
				      + "FROM zcart c JOIN zitem i ON c.item_num = i.item_num) "
			    + "WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return total;
	}
	
	
	//장바구니 목록
	public List<CartVO> getListCart(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartVO> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM zcart c JOIN zitem i ON c.item_num = i.item_num "
				+ "WHERE c.mem_num = ? ORDER BY i.item_num ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			list = new ArrayList<CartVO>();
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setCart_num(rs.getInt("cart_num"));
				cart.setItem_num(rs.getInt("item_num"));
				cart.setOrder_quantity(rs.getInt("order_quantity"));
				cart.setMem_num(rs.getInt("mem_num"));
				//상품정보를 담기 위해 ItemVO 객체 생성 (CartVO에 ItemVO를 미리 담아놓음)
				ItemVO item = new ItemVO();
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setPhoto1(rs.getString("photo1"));
				item.setQuantity(rs.getInt("quantity"));
				item.setStatus(rs.getInt("status"));
				//ItemVO를 CartVO에 저장
				cart.setItemVO(item);
				//같은 상품(item_num이 같음)의 총 구매 금액 구하기
				cart.setSub_total(cart.getOrder_quantity() * item.getPrice());
				//ArrayList에 저장
				list.add(cart);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	//장바구니 상세
	public CartVO getCart(CartVO cart) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartVO cartSaved = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM zcart WHERE item_num=? AND mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getItem_num());
			pstmt.setInt(2, cart.getMem_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cartSaved = new CartVO();
				cartSaved.setCart_num(rs.getInt("cart_num"));
				cartSaved.setItem_num(rs.getInt("item_num"));
				cartSaved.setOrder_quantity(rs.getInt("order_quantity"));
			}
		}catch (Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return cartSaved;
	}
	
	
	//장바구니 수정(개별 상품 수량 수정)
	public void updateCart(CartVO cart) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE zcart SET order_quantity=? WHERE cart_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getOrder_quantity());
			pstmt.setInt(2, cart.getCart_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//장바구니 상품번호와 회원번호별 수정
	
	
	
	//장바구니 삭제
	public void deleteCart(int cart_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM zcart WHERE cart_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
