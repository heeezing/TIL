package kr.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
					  throws ServletException, IOException{ 
		//공통 메서드 호출
		requestPro(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request,
					   HttpServletResponse response)
					   throws ServletException, IOException{
		//공통 메서드 호출
		requestPro(request, response);
	}
	
	//흐름 제어를 위한 공통 메서드 생성
	private void requestPro(HttpServletRequest request,
							HttpServletResponse response)
							throws ServletException, IOException{
		request.setAttribute("result", "하하호호");
		
		//forward 방식으로 jsp 호출
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/messageView.jsp");
		dispatcher.forward(request, response);
	}
	
	
}
