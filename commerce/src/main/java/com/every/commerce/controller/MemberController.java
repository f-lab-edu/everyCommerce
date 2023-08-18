package com.every.commerce.controller;


import com.every.commerce.dto.UserDTO;
import com.every.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;


@Controller

public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/api/v1/members")
	public String create(@RequestBody UserDTO dto) {

		String id = null;
		try {
			id = memberService.join(dto);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return id;
	}
	@PostMapping("/api/v1/test")
	public String create() {


		return "test";
	}


}
