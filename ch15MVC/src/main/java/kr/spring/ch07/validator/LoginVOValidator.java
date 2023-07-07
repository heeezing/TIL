package kr.spring.ch07.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch07.vo.LoginVO;

public class LoginVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginVO vo = (LoginVO)target;
		if(vo.getUserId() == null || vo.getUserId().trim().isEmpty()) {
			//유효성 체크 결과 오류 발생 시
			errors.rejectValue("userId", "required"); //필드, 오류코드
		}
		if(vo.getPassword() == null || vo.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required");
		}
	}

}
