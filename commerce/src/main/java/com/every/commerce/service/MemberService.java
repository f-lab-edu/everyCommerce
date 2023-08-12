package com.every.commerce.service;

import com.every.commerce.domain.Member;
import com.every.commerce.dto.UserDTO;
import com.every.commerce.repository.LoginRepository;
import com.every.commerce.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private LoginRepository loginRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + username));
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		return new User(member.getEmail(), member.getPassword(), authorities);

	}
	//회원가입
	public String join(UserDTO dto) {

		Optional<Member> member = memberRepository.findById(dto.getId());
		if(!member.isPresent()){
			try{
				BeanUtils.copyProperties(member.get(), dto);
			}catch (Exception e){
				log.error("{}",e);
			}
			memberRepository.save(member.get());
			return member.get().getId();
		}else {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}

	}


}
