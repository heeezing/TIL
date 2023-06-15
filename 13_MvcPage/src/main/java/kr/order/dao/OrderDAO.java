package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.util.DBUtil;

public class OrderDAO {
	//싱글턴 패턴
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInstance() {
		return instance;
	}
	private OrderDAO() {}
	
	//주문 등록
	public void insertOrder(OrderVO order, List<OrderDetailVO> orderDetailList) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		String sql = null;
		int order_num = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); //오토 커밋 해제
			//order_num 구하기
			//주문 정보 저장
			//주문 상세 정보 저장
			//상품의 재고 수 차감
			//장바구니에서 주문 상품 삭제
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	
	//[관리자] 전체 주문 개수 / 검색 주문 개수
	//[관리자] 전체 목록 / 검색 주문 목록
	//[사용자] 전체 주문 개수 / 검색 주문 개수
	//[사용자] 전체 주문 목록 / 검색 주문 목록
	//개별 상품 목록
	//주문 삭제 (삭제 시 재고 - 원상 복귀X / 주문 취소 시 원상 복귀 O)
	//[관리자&사용자] 주문 상세
	//[관리자&사용자] 주문 수정
	//[사용자] 주문 취소
}
