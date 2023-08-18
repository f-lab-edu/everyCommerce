package com.every.commerce;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() throws Exception {
		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Test
	public void encode() {
		String password = "1234";

		String encPassword = passwordEncoder.encode(password);

		assertThat(passwordEncoder.matches(password, encPassword)).isTrue();
		assertThat(encPassword).contains("{bcrypt}");
	}

}
