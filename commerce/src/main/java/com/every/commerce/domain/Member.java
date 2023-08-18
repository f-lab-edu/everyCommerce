package com.every.commerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Entity
@Table(name = "members")
@DynamicUpdate
@Getter
@Setter
public class Member {
	@Id
	@Column(name = "memberId")
	private String id;

	@Column
	private String name;

	@Column
	@JsonIgnore
	private String password;

	@Column
	private String email;

	@Column
	private String cellphone;

	@Column
	private Authority grade;

	public void encryptPassword(PasswordEncoder passwordEncoder) {
		password = passwordEncoder.encode(password);
	}

	public void registerUser(PasswordEncoder passwordEncoder) {
		password = passwordEncoder.encode(password);
	}

}
