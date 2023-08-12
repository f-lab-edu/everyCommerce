package com.every.commerce.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@NonNull
	private String id;
	@NonNull
	private String name;
	@NonNull
	private String password;
	@NonNull
	private String email;
	@NonNull
	private String cellphone;



}
