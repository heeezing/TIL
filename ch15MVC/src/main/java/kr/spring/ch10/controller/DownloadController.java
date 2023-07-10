package kr.spring.ch10.controller;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware{
			//자식 타입
	private WebApplicationContext context;
	
	
	@Override						  //부모 타입
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext)applicationContext; //다운캐스팅
	}
	
	@RequestMapping("/file.do")
	public ModelAndView download() throws Exception{		  //상대 경로
		String path = context.getServletContext().getRealPath("/WEB-INF/file.txt");
		File downloadFile = new File(path); //절대 경로를 만들어 속성값에 저장
								//뷰 이름 		속성명			속성값
		return new ModelAndView("download", "downloadFile", downloadFile);
	}
}
