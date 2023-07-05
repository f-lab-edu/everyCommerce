package com.every.commerce.controller;

import com.every.commerce.dto.User;
import com.every.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 로그인
	 * @return
	 */
	@GetMapping("/login")
	public void login(){
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/api/login")
	public ModelAndView test(@RequestParam String id, String pw){
		ModelAndView mv = new ModelAndView();
		memberService.login(id, pw);
		return mv;
	}
}
