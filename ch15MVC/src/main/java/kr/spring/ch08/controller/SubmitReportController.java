package kr.spring.ch08.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch08.validator.SubmitReportValidator;
import kr.spring.ch08.vo.SubmitReportVO;

@Controller
public class SubmitReportController {
	//파일 업로드 경로 읽기 (표기법이 el과 같다)
	//servlet-context.xml에 등록이 되어있기 때문에 읽어올 수 있다.
	@Value("${file_path}")
	private String path;
	
	//자바빈(VO) 초기화 (@ModelAttribute 생략 불가)
	//스프링 유효성 체크엔 자바빈 초기화가 필수. 자바스크립트 이용할거면 필요X.
	@ModelAttribute("report")
	public SubmitReportVO initCommand() {
		return new SubmitReportVO();
	}
	
	@GetMapping("/report/submitReport.do")
	public String form() {
		return "report/submitReportForm";
	}
	
	@PostMapping("/report/submitReport.do")
	public String submit(@ModelAttribute("report") SubmitReportVO vo, BindingResult result) throws IllegalStateException, IOException {
		//전송된 데이터 유효성 체크
		new SubmitReportValidator().validate(vo, result);
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//MultipartFile에 저장되어 있는 파일을 가져와서!
		File file = new File(path + "/" + vo.getReportFile().getOriginalFilename());
		//지정한(원하는) 경로에 transferTo()를 통해 파일 저장.
		vo.getReportFile().transferTo(file); //마우스 대고 Add Throw Declaration 클릭하여 예외처리
		
		System.out.println("주제 : " + vo.getSubject());
		System.out.println("업로드한 파일 : " + vo.getReportFile().getOriginalFilename());
		System.out.println("소스 코드 크키 : " + vo.getReportFile().getSize());
		
		return "report/submittedReport";
	}
}
