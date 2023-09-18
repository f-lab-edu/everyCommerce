package com.everycommerce.productservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-service")
public class ProductController {
	@RequestMapping("/")
	public String home() {
		return "forward:/hello.html";
	}
}
