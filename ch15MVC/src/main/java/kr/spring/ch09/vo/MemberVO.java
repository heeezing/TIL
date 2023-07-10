package kr.spring.ch09.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class MemberVO {
	//정규표현식으로 패턴 검사 (숫자, 영어만 가능)
	@Pattern(regexp="^[0-9a-zA-Z]+$")
	private String id;
	
	//문자열의 길이 지정 (4자 이상, 10자 이하)
	@Size(min=4,max=10)
	private String password;
	
	//필수 입력 처리
	@NotEmpty
	private String name;
	
	//숫자의 길이 지정 (외부 라이브러리 - pom.xml에 등록해놓음)
	@Range(min=1,max=200) 
	private int age;
	
	//이메일 형식 + 필수 입력 처리
	@Email
	@NotEmpty
	private String email;
	
	/*--------------------------------------------*/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", age=" + age + ", email=" + email
				+ "]";
	}
}
