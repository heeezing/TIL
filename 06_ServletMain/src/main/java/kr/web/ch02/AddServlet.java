package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
					  throws ServletException,IOException{
		/*
		 * [실습]
		 * 전송된 두 개의 정수를 연산
		 * [출력 예시]
		 * 5 + 4 = 9
		 */
		
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>덧셈 프로그램</title></head>");
		out.println("<body>");
		out.printf("%d + %d = %d", num1, num2, num1+num2);
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
