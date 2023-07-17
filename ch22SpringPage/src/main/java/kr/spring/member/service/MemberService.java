package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	
	/*-----[회원 관리 - 일반 회원]-----*/
	
	//회원 가입
	public void insertMember(MemberVO member);
	
	//ID를 이용한 회원 정보 체크
	public MemberVO selectCheckMember(String id);
	//회원 정보 (회원 번호를 이용)
	public MemberVO selectMember(Integer mem_num);
	//회원 정보 수정
	public void updateMember(MemberVO member);
	//비밀번호 수정
	public void updatePassword(MemberVO member);
	//회원 탈퇴
	public void deleteMember(Integer mem_num);
	//자동 로그인
	public void updateAu_id(String au_id, int mem_num);
	public void selectAu_id(String au_id);
	public void deleteAu_id(int mem_num);
	
}
