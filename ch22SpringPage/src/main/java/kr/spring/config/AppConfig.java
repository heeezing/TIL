package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.AdminCheckInterceptor;
import kr.spring.interceptor.AutoLoginCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;

//자바 코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	/*==========인터셉터==========*/
	
	private AutoLoginCheckInterceptor autoLoginCheck;
	private LoginCheckInterceptor loginCheck;
	private AdminCheckInterceptor adminCheck;
	
	@Bean
	public AutoLoginCheckInterceptor interceptor() {
		autoLoginCheck = new AutoLoginCheckInterceptor();
		return autoLoginCheck;
	}
	
	@Bean
	public LoginCheckInterceptor interceptor2() {
		loginCheck = new LoginCheckInterceptor(); //객체 생성해서
		return loginCheck; //컨테이너에 보관
	}
	
	@Bean
	public AdminCheckInterceptor interceptor3() {
		adminCheck = new AdminCheckInterceptor();
		return adminCheck;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//AutoLoginCheckInterceptor 설정
		registry.addInterceptor(autoLoginCheck)
				.addPathPatterns("/**") //모든 요청
				//로그인 관련 페이지 진입 시 자동 로그인 기능 무력화
				.excludePathPatterns("/member/login.do") 
				.excludePathPatterns("/member/logout.do");
		//LoginInterceptor 설정
		registry.addInterceptor(loginCheck) //등록
				.addPathPatterns("/member/myPage.do") //loginCheck 동작 위치 지정
				.addPathPatterns("/member/update.do")
				.addPathPatterns("/member/changePassword.do")
				.addPathPatterns("/member/delete.do")
				.addPathPatterns("/board/write.do")
				.addPathPatterns("/board/update.do")
				.addPathPatterns("/board/delete.do");
		//AdminInterceptor 설정
		registry.addInterceptor(adminCheck)
				.addPathPatterns("/main/admin.do")
				.addPathPatterns("/member/admin_list.do")
				.addPathPatterns("/member/admin_update.do");
	}
	
	
	/*==========타일스==========*/
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		//저장된 주소값을 변동할 수 없게 상수 처리 
		final TilesConfigurer configurer = new TilesConfigurer();
		//해당 경로에 xml 설정 파일을 넣음 (보통 여러 개니까 배열 형태로.)
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/member.xml",
				"/WEB-INF/tiles-def/board.xml"
		});
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
}
