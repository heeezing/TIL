package kr.boardlevel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//최초에 글을 쓸 때는 부모글이 생성됨 (0을 기본값으로 세팅)
		int parent_num = 0, depth = 0;
		if(request.getParameter("parent_num") != null) {
			//null이 아니다 => 부모글이 존재한다 => 답글로 작성됨
			parent_num = Integer.parseInt(request.getParameter("parent_num"));
			depth = Integer.parseInt(request.getParameter("depth"));
		}
		request.setAttribute("parent_num", parent_num);
		request.setAttribute("depth", depth);
		
		return "/WEB-INF/views/boardLevel/writeForm.jsp";
	}

}
