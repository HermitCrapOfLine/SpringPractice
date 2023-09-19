package org.galapagos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j;

@Controller
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 요청에 대한 권한 설정
				.antMatchers("/security/all").permitAll() // 모두허용
				.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')");

		http.formLogin()
		.loginPage("/security/login")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/") // 로그인 페이지에서 바로 성공했을 때 홈으로 이동한다.
		.failureUrl("/security/login?error=true"); // el : param.error
		
		http.logout() // POST 요청으로 처리해야한다. CSRF 토큰 필요 !
			.logoutUrl("/security/logout") // POST: 로그아웃 호출 url
			.invalidateHttpSession(true) // 세션 invalidate
			.deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
			.logoutSuccessUrl("/security/logout"); // 로그아웃 이후 이동할 페이지
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("configure .........................................");
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("$2a$10$4QBgJcERpeXunP4zEFvnmOXQbOpNTIyLmbKlgFOHOzJpIUdQpwQ7q")
		.roles("ADMIN");
		
		auth.inMemoryAuthentication()
		.withUser("member")
//		.password("{noop}1234")
		.password("$2a$10$4QBgJcERpeXunP4zEFvnmOXQbOpNTIyLmbKlgFOHOzJpIUdQpwQ7q")
		.roles("MEMBER");
	}
	

}
