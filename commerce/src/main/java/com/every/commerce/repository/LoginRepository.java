package com.every.commerce.repository;

import com.every.commerce.domain.Member;
import com.every.commerce.dto.Members;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepository {

	private final String ID = "123";
	private final String PW = "123";

	public Optional<Members> findByLoginId(String id, String password) {
		Members user = new Members();
		if(id.equals(ID)){
			user.setName("조현수");
			user.setPw(PW);
		}else {

		}
		return Optional.of(user);
	}

	public Optional<Member> findByMemberId(String id){
		Member member= new Member();
		member.setAge(20);
		member.setId(123L);
		member.setUsername("조현수");

		return Optional.of(member);
	}

}
