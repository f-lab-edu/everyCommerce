package com.every.commerce.service;

import com.every.commerce.dto.User;
import com.every.commerce.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private LoginRepository loginRepository;

	/**
	 *
	 * @param loginId
	 * @param password
	 * @return
	 */
	public User login(String loginId, String password){

		Optional<User> userOptional = loginRepository.findByLoginId(loginId, password);
		User user = userOptional.get();

		if(user.getPw().equals(password)){
			return user;
		}else {
			return null;
		}

	}
}
