package kr.spring.ch06.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch06.vo.MemberInfo;

public class MemberInfoValidator implements Validator{
	
	//Validator가 검증할 수 있는 타입인지를 검사
	@Override
	public boolean supports(Class<?> clazz) {
		//자바빈(MemberInfo)이 유효성 체크가 되는 객체인지를 검사
		return MemberInfo.class.isAssignableFrom(clazz);
	}

	//유효성 체크를 수행하는 메서드
	//target객체에 대한 검증을 실행(자바빈이 들어옴),
	//검증 결과 문제가 있을 경우 errors객체에 어떤 문제인지 정보를 저장.
	@Override
	public void validate(Object target, Errors errors) {
		MemberInfo memberInfo = (MemberInfo)target;
		if(memberInfo.getId() == null || memberInfo.getId().trim().isEmpty()) {
			//유효성 체크 결과 오류가 발생하는 경우
							  //필드    에러코드
			errors.rejectValue("id", "required");
		}
		if(memberInfo.getName() == null || memberInfo.getName().trim().isEmpty()) {
			errors.rejectValue("name", "required");
		}
		if(memberInfo.getZipcode() == null || memberInfo.getZipcode().trim().isEmpty()) {
			errors.rejectValue("zipcode", "required");
		}
		if(memberInfo.getAddress1() == null || memberInfo.getAddress1().trim().isEmpty()) {
			errors.rejectValue("address1", "required");
		}
		if(memberInfo.getAddress2() == null || memberInfo.getAddress2().trim().isEmpty()) {
			errors.rejectValue("address2", "required");
		}
	}
}
