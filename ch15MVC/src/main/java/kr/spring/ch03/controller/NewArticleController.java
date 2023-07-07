package kr.spring.ch03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.ch03.service.ArticleService;
import kr.spring.ch03.vo.NewArticleVO;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewArticleController {
	
	@Autowired //의존 관계
	private ArticleService articleService;
	
	/* @Autowired를 사용할 때는 private이어도 set메서드 생략 가능
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}*/
	
	//@RequestMapping(value="/article/newArticle.do", method=RequestMethod.GET)
	@GetMapping("/article/newArticle.do")
	public String form() { //문자열로 전달해도 jsp로 인식함.
		return "article/newArticleForm"; //view 이름
	}
	
	/*
	 * [@ModelAttribute 어노테이션]을 이용해서 전송된 데이터를 자바빈에 담기
	 * 
	 * [기능]
	 * 1. 자바빈(VO) 생성
	 * 2. 전송된 데이터를 자바빈에 저장
	 * 3. View에서 사용할 수 있도록 request에 자바빈(VO)를 저장
	 * 
	 * [형식]
	 * 1. @ModelAttribute(속성명) NewArticleVO newArticleVO
	 * 		-> 지정한 속성명으로 JSP에서 request에 접근해서 자바빈(VO) 호출
	 * 		   예) ${속성명.title}
	 * 2. @ModelAttribute NewArticleVO newArticleVO
	 * 		-> 속성명을 생략할 수도 있음
	 * 		   속성명은 클래스명의 첫글자를 소문자로 바꾸는 형식으로 자동 생성
	 * 3. NewArticleVO newArticleVO 
	 * 		-> @ModelAttribute 생략
	 * 		   호출 메서드의 인자명만 명시
	 * 		   속성명은 클래스명의 첫글자를 소문자로 바꾸는 형식으로 자동 생성
	 */
	
	
	//@RequestMapping(value="/article/newArticle.do", method=RequestMethod.POST)
	@PostMapping("/article/newArticle.do")
	public String submit(NewArticleVO newArticleVO) {
		//@ModelAttribute가 newArticleVO를 생성하고, 
		//멤버변수와 request에 있는 name이 같으면 자동으로 데이터를 넣어줌.

		articleService.writeArticle(newArticleVO); //DB연동했다고 가정~
		
		return "article/newArticleSubmitted";
	}
}
