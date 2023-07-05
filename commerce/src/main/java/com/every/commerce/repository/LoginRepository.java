package com.every.commerce.repository;

import com.every.commerce.dto.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepository {

	private final String ID = "123";
	private final String PW = "123";

	public Optional<User> findByLoginId(String id, String password) {
		User user = new User();
		if(id.equals(ID)){
			user.setName("조현수");
			user.setPw(PW);
		}else {

		}
		return Optional.of(user);
	}


}
