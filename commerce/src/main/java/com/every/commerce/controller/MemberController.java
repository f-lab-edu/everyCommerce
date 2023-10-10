package com.every.commerce.controller;


import com.every.commerce.dto.UserDTO;
import com.every.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;


@Controller
@RequestMapping("/member-service")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/")
	public String home() {
		return "hello";
	}

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

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
	@GetMapping("/api/v1/test")
	public void test(){
		System.out.println("test");
	}


}
