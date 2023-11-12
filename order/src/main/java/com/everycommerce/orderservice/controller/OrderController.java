package com.everycommerce.orderservice.controller;

import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.service.OrderSerive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-service")
public class OrderController {
	OrderSerive orderSerive;

	public OrderController(OrderSerive orderSerive){
		this.orderSerive = orderSerive;
	}

	@GetMapping("/")
	public String home(){
		return "hello Orderservice";
	}

	@PostMapping("/api/newOrder")
	public String createOrder(@RequestBody OrderDTO orderDTO){
		orderSerive.createOrder(orderDTO);
		return "dd";
	}

}
