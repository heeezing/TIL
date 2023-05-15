package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hundred")
public class HundredServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
					  throws ServletException,IOException{
		/*
		 * [실습]
		 * 1부터 100까지의 합 출력 (반복문 활용)
		 * [출력 예시]
		 * 1부터 100까지의 합은 5050이다.
		 */
		
		int total = 0;
		for(int i=1 ; i<=100 ; i++){
			total += i;
		}
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//HTML 출력을 위한 스트림 생성		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>1부터 100까지의 합</title></head>");
		out.println("<body>");
		out.println("1부터 100까지의 합은 " +  total + "이다.");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
