package com.every.commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
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
}
