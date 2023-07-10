package kr.spring.ch08.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch08.vo.SubmitReportVO;

public class SubmitReportValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SubmitReportVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SubmitReportVO vo = (SubmitReportVO)target;
		if(vo.getSubject() == null || vo.getSubject().trim().isEmpty()) {
			//유효성 체크 결과 오류 발생 시
			errors.rejectValue("subject", "required");
		}
		if(vo.getReportFile() == null || vo.getReportFile().isEmpty()) {
			//유효성 체크 결과 오류 발생 시
			errors.rejectValue("reportFile", "required");
		}
	}
}
