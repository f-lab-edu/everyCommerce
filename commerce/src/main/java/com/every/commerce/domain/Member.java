package com.every.commerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

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


	public enum Authority {
		ADMIN,
		OPERATOR,
		MEMBER
	}
}
