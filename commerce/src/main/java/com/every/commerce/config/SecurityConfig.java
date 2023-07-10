package com.every.commerce.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

		http.authorizeRequests(authorize -> {
			try{
				authorize
						.antMatchers("/main")
						.permitAll() // "/main"은 권한 필요없이 접근가능
				.and()
						.formLogin()
						.loginPage("/login") //로그인페이지
						.loginProcessingUrl("/api/login") //이 url을 던지면 spring security에서 인터셉터해온다
						.defaultSuccessUrl("/main",true) //로그인이 성공하면 이 주소로 이동하게 된다.
						;
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return http.build();
	}

}

