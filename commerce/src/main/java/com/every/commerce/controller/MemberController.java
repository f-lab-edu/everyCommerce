package com.every.commerce.controller;


import com.every.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 로그인
	 * @return
	 */
	@RequestMapping("/login")
	public String home() {
		return "login";
	}

	/**
	 *
	 * @return9
	 */
/*	@PostMapping("/api/login")
	public String test(@RequestParam String id, String pw){
		ModelAndView mv = new ModelAndView();
		memberService.login(id, pw);
		return "main";
	}*/

}
