package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response)
								  throws Exception {
		HttpSession session = request.getSession();
		//로그아웃 처리
		session.invalidate();
		//JSP 경로 반환(별도의 jsp파일 생성할 필요X. redirect처리.)
		return "redirect:/main/main.do";
	}

}
