package kr.employee.dao;

import kr.employee.vo.EmployeeVO;
import kr.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {
	/*
	 * 싱글턴 패턴은 생성자를 private으로 지정해서 외부에서 호출할 
	 * 수 없도록 처리하고 static 메서드를 호출해서 객체가 한 번만
	 * 생성되고 생성된 객체를 공유할 수 있도록 처리하는 방식을 의미함
	 */
	private static EmployeeDAO instance = new EmployeeDAO();

	public static EmployeeDAO getInstance(){
		return instance;
	}

	private EmployeeDAO(){}

	//사원가입
	public void insertEmployee(EmployeeVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;

		try{
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			//SQL문
			sql = "insert into semployee (num,id,name,passwd,salary"
					+ ",job,reg_date) values "
					+ "(semployee_seq.nextval,?,?,?,?,?,sysdate)";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, member.getId());
			pstmt.setString(++cnt, member.getName());
			pstmt.setString(++cnt, member.getPasswd());
			pstmt.setInt(++cnt, member.getSalary());
			pstmt.setString(++cnt, member.getJob());

			//SQL문 반영
			pstmt.executeUpdate();

		}catch(Exception e){
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(null, pstmt, conn);
		}

	}
	//사원상세정보
	public EmployeeVO getEmployee(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO member = null;
		String sql = null;

		try{
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			//SQL문
			sql = "SELECT * FROM semployee WHERE num=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			//SQL문 반영하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();

			if(rs.next()){
				member = new EmployeeVO(); //자바빈 객체 생성
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setSalary(rs.getInt("salary"));
				member.setJob(rs.getString("job"));
				member.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//아이디 중복 체크, 로그인 체크
	public EmployeeVO checkEmployee(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO member = null;
		String sql = null;

		try{
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			//SQL문
			sql = "SELECT * FROM semployee WHERE id=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			//SQL문 반영하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();

			if(rs.next()){
				member = new EmployeeVO(); //자바빈 객체 생성
				member.setId(rs.getString("id"));
				member.setNum(rs.getInt("num"));
				member.setPasswd(rs.getString("passwd"));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//사원정보수정
	public void updateEmployee(EmployeeVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;

		try{
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 
			sql = "update semployee set name=?,passwd=?,"
					+ "salary=?,job=? "
					+ "where num=?";
			//PreparedStatement 객체를 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, member.getName());
			pstmt.setString(++cnt, member.getPasswd());
			pstmt.setInt(++cnt, member.getSalary());
			pstmt.setString(++cnt, member.getJob());
			pstmt.setInt(++cnt, member.getNum());
			//SQL문 반영
			pstmt.executeUpdate();

		}catch(Exception e){
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//사원탈퇴(사원정보 삭제)
	public void deleteEmployee(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try{
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문
			sql = "delete from semployee where num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			//SQL문 반영
			pstmt.executeUpdate();

		}catch(Exception e){
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(null, pstmt, conn);
		}

	}
}
