package com.every.commerce.controller;


import com.every.commerce.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller

public class MemberController {

	@Autowired
	private MemberService memberService;



}
