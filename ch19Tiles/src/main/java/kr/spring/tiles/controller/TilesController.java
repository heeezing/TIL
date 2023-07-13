package kr.spring.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesController {
	@RequestMapping("/main.do")
	public String main() {
		return "main"; //타일스 설정의 식별자(tilesdef.xml에 명시해놓음) / main.jsp 아님!
	}
	
	@RequestMapping("/company.do")
	public String getAbout() {
		return "company"; //타일스 설정의 식별자
	}
	
	@RequestMapping("/product.do")
	public String getProduct() {
		return "product"; //타일스 설정의 식별ㅈ바
	}
}
