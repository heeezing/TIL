package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler/*:우리가 호출하는 컨트롤러*/) throws Exception{
		logger.debug("<<LoginCheckInterceptor 진입>>");
		
		HttpSession session = request.getSession();
		//로그인 여부 검사
		if(session.getAttribute("user") == null) {
			//로그인 X
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return false; //요청하는 페이지가 동작되지않음 (대신 로그인 페이지가 동작)
		}
		//로그인 O
		return true; //요청하는 페이지가 동작됨
	}
	
}
