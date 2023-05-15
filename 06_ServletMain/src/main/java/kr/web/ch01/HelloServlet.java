package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//일반 클래스에 HttpServlet을 상속하면
//주소에 따라 동작되게끔 하는 Servlet이 되고
//HTML을 생성해서 클라이언트로 전송할 수 있게 됨.
@WebServlet("/helloServlet") //주소 생성
public class HelloServlet extends HttpServlet{
	@Override //재정의 잘 되었는 지 확인
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
							  throws ServletException,IOException{
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=UTF-8");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");		
		out.println("<head><title>Hello Servlet</title></head>");		
		out.println("<body>");		
		out.println("처음 작성하는 Servlet입니다.");
		out.println("</body>");
		out.println("</html>");		
		out.close(); //자원 정리
	}
}
