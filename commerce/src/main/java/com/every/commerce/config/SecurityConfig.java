package com.every.commerce.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@EnableWebSecurity
/*@AllArgsConstructor*/
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

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
		http.csrf(csrf -> {
			try {
				csrf.ignoringAntMatchers("/api/v1/members");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return http.build();
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		// 지원하는 인코딩 방식 매핑
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());

		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}
}

