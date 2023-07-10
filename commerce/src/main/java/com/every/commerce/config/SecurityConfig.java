package com.every.commerce.config;

import com.every.commerce.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}*/


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/*		http.csrf().disable()
				.authorizeRequests()
				.anyRequest().permitAll();*/ //-> csrf를 막는 코드

		http.authorizeRequests() //요청을 어떻게 보안을 걸것이냐.
				.antMatchers("/main").permitAll() //이 패턴에 해당하는 요청은 허용하자
			/*	.anyRequest().authenticated() //이 외의 나머지요청들은 인증이 필요하다*/
			.and()
				.formLogin()
				.loginPage("/login")//로그인페이지로 이동할수있게끔
				.permitAll()//login페이지는 인증없이 사용가능
				.loginProcessingUrl("/api/login")
				.defaultSuccessUrl("/main",true)
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/test")
		;
		return http.build();
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails userDetails = User
				.withUsername("123")
				.password("123")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(userDetails);
	}






	/*@Override
	public void configure(HttpSecurity http) throws Exception {
		security.csrf().disable().authorizeRequests().anyRequest().permitAll();
*//*		http.authorizeRequests()
				//페이지권한설정
				.antMatchers("/**").permitAll() //패턴에 맞는 모든 요청을 허용한다
		*//**//*		.anyRequest().authenticated() //위의 패턴외에 모든 요청은 인증된 사용자만 접근가능하다*//**//*
		 *//**//*.and()
				.formLogin();*//**//*
				.and()
				.formLogin()
				.loginPage("/user/login")
				.permitAll();
	}*//*
	}*/
}

