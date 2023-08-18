package com.every.commerce.dto;


import com.every.commerce.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PrincipalDTO implements UserDetails {


	private Member member;

	public PrincipalDTO(Member member) {
		this.member= member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collections = new ArrayList<>();
		collections.add(() -> {
			return "ROLE_"+member.getGrade().toString();

		});
		return collections;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getName();
	}

	//계정이 만료되었는가 (true : 만료 x )
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// 계정이 잠겼는지 (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	// 비밀번호가 만료되었는지 (true: 만료X)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인지 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
}
