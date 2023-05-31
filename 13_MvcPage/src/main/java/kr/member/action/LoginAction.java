package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member!=null) { //있는 아이디일 경우
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);
			//로그인 실패 시 auth 체크 용으로 request에 auth값 저장해놓기
			request.setAttribute("auth", member.getAuth());
		}
		if(check) { //아이디, 비밀번호 인증 성공
			//로그인 처리
			HttpSession session = request.getSession(); //session객체 반환
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getId());
			session.setAttribute("user_auth", member.getAuth());
			session.setAttribute("user_photo", member.getPhoto());
			//이제 로그인만 되어있으면 언제든 세션에서 위의 4가지 정보를 가져다 쓸 수 있다.
			
			//인증 성공 시 호출
			//dispatcherServlet이 redirect:를 읽고 보내줌
			return "redirect:/main/main.do";
		}
		
		//인증 실패 시 호출
		return "/WEB-INF/views/member/login.jsp";
	}

}
