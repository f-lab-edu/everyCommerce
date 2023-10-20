package com.everycommerce.orderservice.controller;

import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.service.OrderSerive;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("/api/newOrder")
	public String createOrder(OrderDTO orderDTO){
		orderSerive.createOrder(orderDTO);
		return "dd";
	}

}
