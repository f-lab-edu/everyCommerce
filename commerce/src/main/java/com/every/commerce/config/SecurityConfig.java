package com.every.commerce.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
/*@AllArgsConstructor*/
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/*http.csrf().disable()
				.authorizeRequests()
				.anyRequest().permitAll();
		return http.build();*/
		http.authorizeRequests(authorize -> {
			try {
				authorize
						.antMatchers("/main")
						.permitAll() // "/main"은 권한 필요없이 접근가능 2.get요청만 허용한다.
						.antMatchers(HttpMethod.POST, "/api/v1/members")
						.permitAll()
						.and()
						.formLogin()
						.loginPage("/login") //로그인페이지
						.loginProcessingUrl("/api/login") //이 url을 던지면 spring security에서 인터셉터해온다
						.defaultSuccessUrl("/main", true) //로그인이 성공하면 이 주소로 이동하게 된다.
				;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		/*특정 요청에 대해서 csrf 제외*/
		http.csrf(csrf ->{
			try {
				csrf.ignoringAntMatchers("/api/v1/members");
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return http.build();
	}

}

