package com.every.commerce.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {



	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable();
/*		http.authorizeRequests()
				//페이지권한설정
				.antMatchers("/**").permitAll() //패턴에 맞는 모든 요청을 허용한다
		*//*		.anyRequest().authenticated() //위의 패턴외에 모든 요청은 인증된 사용자만 접근가능하다*//*
		 *//*.and()
				.formLogin();*//*
				.and()
				.formLogin()
				.loginPage("/user/login")
				.permitAll();
	}*/
	}
}

