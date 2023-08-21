package com.every.commerce.service;

import com.every.commerce.domain.Authority;
import com.every.commerce.domain.Member;
import com.every.commerce.dto.PrincipalDTO;
import com.every.commerce.dto.UserDTO;
import com.every.commerce.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;

@Service
@Slf4j
@Transactional
/*@AllArgsConstructor //모든필드의 생성자를 자동으로 추가해준다.*/
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findById(username).orElseThrow(() ->
				new UsernameNotFoundException("User not found with id : " + username));

		return new PrincipalDTO(member);
	}

	//회원가입
	public String join(UserDTO dto) throws InvocationTargetException, IllegalAccessException {

		Boolean exMember = memberRepository.existsById(dto.getId());
		if (exMember) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}

		Member member = new Member();

		BeanUtils.copyProperties(member, dto);
		member.registerUser(passwordEncoder);
		member.setGrade(Authority.MEMBER);

		memberRepository.save(member);
		return member.getId();
	}


}
