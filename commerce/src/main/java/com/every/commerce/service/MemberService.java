package com.every.commerce.service;

import com.every.commerce.domain.Member;
import com.every.commerce.dto.Members;
import com.every.commerce.repository.LoginRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
/*@AllArgsConstructor //모든필드의 생성자를 자동으로 추가해준다.*/
public class MemberService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println("시작");
/*		Optional<Member> userOptional = loginRepository.findByMemberId(username);
		Member member = userOptional.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		return new User(member.getEmail(), member.getPw(), authorities);*/
		System.out.println(id);
		return null;
	}

}
