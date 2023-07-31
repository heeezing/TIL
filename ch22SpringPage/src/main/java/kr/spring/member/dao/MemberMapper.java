package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	/*-----[회원 관리 - 일반 회원]-----*/
	
	//회원 번호 생성
	@Select("SELECT spmember_seq.nextval FROM dual")
	public int selectMem_num();
	
	//회원 가입
	@Insert("INSERT INTO spmember (mem_num,id,nick_name) VALUES (#{mem_num},#{id},#{nick_name})")
	public void insertMember(MemberVO member);
	
	public void insertMember_detail(MemberVO member); //xml에 생성
	
	//ID를 이용한 회원 정보 체크
	public MemberVO selectCheckMember(String id); //xml에 생성
	
	//회원 정보 (회원 번호를 이용)
	@Select("SELECT * FROM spmember m JOIN spmember_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	
	//회원 정보 수정
	@Update("UPDATE spmember SET nick_name=#{nick_name} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	
	public void updateMember_detail(MemberVO member);
	
	//비밀번호 수정
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	//회원 탈퇴
	@Update("UPDATE spmember SET auth=0 WHERE mem_num=#{mem_num}") //탈퇴회원으로 auth정보변경,
	public void deleteMember(Integer mem_num);
	@Delete("DELETE FROM spmember_detail WHERE mem_num=#{mem_num}") //개인정보는 삭제
	public void deleteMember_detail(Integer mem_num);
	
	//자동 로그인
	//원래 mybatis는 하나의 인자만 넣을 수 있지만, 어노테이션을 통해 여러 인자를 넣을 수 있다.
	@Update("UPDATE spmember_detail SET au_id=#{au_id} WHERE mem_num=#{mem_num}")
	public void updateAu_id(@Param("au_id") String au_id, @Param("mem_num")int mem_num);
	
	@Select("SELECT m.mem_num,m.id,m.auth,d.au_id,d.passwd,m.nick_name,d.email "
		  + "FROM spmember m JOIN spmember_detail d ON m.mem_num = d.mem_num "
		  + "WHERE d.au_id = #{au_id}")
	public MemberVO selectAu_id(String au_id);
	
	@Update("UPDATE spmember_detail SET au_id='' WHERE mem_num=#{mem_num}")
	public void deleteAu_id(int mem_num); //쿠키,au_id 크로스체크하기때문에 au_id만 지워도 괜찮다.
	
	//프로필 이미지 업데이트
	@Update("UPDATE spmember_detail SET photo=#{photo},photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	
	/*-----[회원 관리 - 관리자]-----*/
	
	//전체or검색 레코드 수
	public int selectRowCount(Map<String,Object> map);
	//전체or검색 목록
	public List<MemberVO> selectList(Map<String,Object> map);
	//회원 권한 변경
	@Update("UPDATE spmember SET auth=#{auth} WHERE mem_num=#{mem_num}")
	public void updateByAdmin(MemberVO memberVO);
	
	
	/*-----[채팅]-----*/
	
	//채팅 회원 정보 검색
	@Select("SELECT mem_num,id,nick_name "
		  + "FROM spmember "
		  + "WHERE auth>=2 AND id LIKE '%' || #{id} || '%'")
	public List<MemberVO> selectSearchMember(String id);
	
}
