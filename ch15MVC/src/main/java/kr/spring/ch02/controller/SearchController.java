package kr.spring.ch02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	/*
	 *  @RequestParam 어노테이션
	 *  : HTTP 요청 파라미터를 메서드의 파라미터로 전달 (request.getParameter과 같은 역할)
	 *  
	 *  [형식]
	 *  1. @RequestParam(요청파라미터명) 메서드파라미터 
	 *  2. 요청 파라미터명과 호출 메서드 인자명이 같으면 @RequestParam의 요청 파라미터명 생략 가능 
	 *  3. 위와 같을 시 @RequestParam도 생략 가능
	 *  
	 *  [주의]
	 *  메서드에 @RequestParam을 명시하면 query는 필수적으로 전달해야 함.
	 *  미전달 시 오류 발행.
	 *  만약 query를 필수적으로 사용하지 않는다면, 
	 *  @RequestParam(value="query",required=false) 로 명시해야 함. query=null로 인식.
	 */
	
	//요청 URL과 실행 메서드 연결
	@RequestMapping("/search/internal.do")		   //파라미터명 불러와서 //여기에 저장
	public ModelAndView searchInternal(@RequestParam("query") String query) {
		/* 요청 파라미터명 = 호출 메서드 인자명 = query로 다 같기 때문에, 아래에 셋 다 가능 */
		//1. searchInternal(@RequestParam("query") String query) - query 미전달 시 오류
		//2. searchInternal(@RequestParam String query) - query 미전달 시 오류
		//3. searchInternal(String query) - query 미전달 해도 오류X. null로 받아들임.
		
		System.out.println("query = " + query);
		
								    //뷰 이름
		return new ModelAndView("search/internal");
		// => /WEB-INF/views/search/internal.jsp
	}
	
	
	@RequestMapping("/search/external.do")
	public ModelAndView searchExternal(@RequestParam(value="query",required=false) String query,
									   @RequestParam(value="p",defaultValue="1") int pageNumber) {
		//required=false 로 명시하면 null이 pageNumber로 들어가면서, int로 parsing되는 과정에서 오류 발생.
		System.out.println("query = " + query);
		System.out.println("p = " + pageNumber);
		
		return new ModelAndView("search/external");
	}
	
}
