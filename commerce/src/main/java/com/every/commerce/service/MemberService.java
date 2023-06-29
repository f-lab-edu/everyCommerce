package com.every.commerce.service;

import com.every.commerce.dto.User;
import com.every.commerce.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private LoginRepository loginRepository;

	public User login(){

		User user = new User();

		return user;
	}
}
