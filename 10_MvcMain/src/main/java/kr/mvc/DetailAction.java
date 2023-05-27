package kr.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse reponse) 
								  throws Exception {
		//request에 데이터 저장
		request.setAttribute("message", "글 정보 상세 페이지입니다.");
		//JSP경로 반환
		return "/views/detail.jsp";
	}
	
}
