package kr.s17.network;

import java.net.MalformedURLException;
import java.net.URL;

public class URLMain {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://java.sun.com/index.jsp?name=kim#content");
			System.out.println("프로토콜 : " + url.getProtocol()); //http
			System.out.println("호스트 : " + url.getHost()); //java.sun.com
			System.out.println("기본포트 : " + url.getDefaultPort()); //80 (프로토콜 http가 가지는 기본포트)
			System.out.println("포트 : " + url.getPort()); //-1 (명시안했기때문)
			System.out.println("패스 : " + url.getPath()); // /index.jsp
			System.out.println("쿼리 : " + url.getQuery()); //name=kim   /key와 value 쌍으로 저장 가능한 형태
			System.out.println("ref : " + url.getRef()); //content  /ref(레퍼런스) : 페이지 내의 특정 위치
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}		
	}
}
