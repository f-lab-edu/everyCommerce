package com.every.commerce.repository;

import com.every.commerce.domain.Member;
import com.every.commerce.dto.Members;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepository {

	private final String ID = "123";
	private final String PW = "123";



	//인메모리데이터를 위해.. 추후에 삭제 예정
	public Optional<Member> findByMemberId(String id){
		Member member= new Member();
		member.setAge(20);
		member.setId(123L);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //스프링시큐리티는 반드시 패스워드를 암호화해야한다.
		member.setPw(passwordEncoder.encode("123"));
		member.setEmail("123@naver.com");
		member.setUsername("조현수");

		return Optional.of(member);
	}

}
