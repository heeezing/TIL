package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/travel")
public class TravelSurvlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request,
					   HttpServletResponse response)
					   throws ServletException, IOException{
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String name = request.getParameter("name");
		String[] cities = request.getParameterValues("city");
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//HTML 출력을 위한 출력 스트림을 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>여행지 선택</title></head>");
		out.println("<body>");
		
		out.println("이름 : " + name + "<br>");
		out.println("여행지 : ");
		
		if(cities != null) {
			for(int i=0 ; i<cities.length ; i++ ) {
				if(i>0) out.print(", ");
				out.print(cities[i]);
				/* 쉼표를 뒤에 넣을 경우
				out.print(cities[i]);
				if(i<cities.length-1) out.print(", ");
				 */
			}
		}else {
			out.println("집~~");
		}
		
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
