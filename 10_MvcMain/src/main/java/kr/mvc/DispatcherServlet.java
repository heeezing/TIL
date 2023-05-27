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
		Action com = null; //모델 클래스 불러올 때 담아올 객체(Action을 자료형으로)
		String view = null; //뷰 정보를 담을 변수 생성
		
		//클라이언트가 호출한 주소(URI형태) 반환
		String command = request.getRequestURI();
		
		//호출한 주소의 뒤에 컨텍스트 경로 다음의 주소
		// /list.do 또는 /detail.do 정보 추출 
		// ex) ch10-MvcMain/list.do 에서 substring 이용해서 추출
		if(command.indexOf(request.getContextPath())==0) { //컨텍스트 경로가 주소 맨 앞에 있는지
														   //ex)cj10-MvMain 이런식으로 오타있으면X
			command = command.substring(request.getContextPath().length());
		}
		
		if(command.equals("/list.do")) {
			//모델 클래스 객체 생성
			com = new ListAction();
		}else if(command.equals("/write.do")) {
			com = new WriteAction();
		}else if(command.equals("/detail.do")) {
			com = new DetailAction();
		}else if(command.equals("/update.do")) {
			com = new UpdateAction();
		}else if(command.equals("/delete.do")) {
			com = new DeleteAction();
		}
		
		try {
			view = com.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//forward 방식으로 jsp 호출
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	
}
