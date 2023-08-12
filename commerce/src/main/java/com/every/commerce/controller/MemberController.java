package com.every.commerce.controller;


import com.every.commerce.dto.UserDTO;
import com.every.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/api/v1/members")
	public String create(UserDTO dto) {

		String id =memberService.join(dto);

		return id;
	}


}
