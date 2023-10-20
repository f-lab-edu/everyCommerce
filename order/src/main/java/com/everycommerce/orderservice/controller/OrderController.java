package com.everycommerce.orderservice.controller;

import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.service.OrderSerive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/order-service")
public class OrderController {
	OrderSerive orderSerive;

	public OrderController(OrderSerive orderSerive){
		this.orderSerive = orderSerive;
	}

	@RequestMapping("/")
	public String home(){
		return "hello Orderservice";
	}

	@GetMapping("/api/newOrder")
	public String createOrder(@RequestBody OrderDTO orderDTO){
		orderSerive.createOrder(orderDTO);
		return "dd";
	}

}
