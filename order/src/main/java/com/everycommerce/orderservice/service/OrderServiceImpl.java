package com.everycommerce.orderservice.service;

import com.everycommerce.orderservice.domain.Order;
import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderSerive{

	OrderRepository orderRepository;
	public OrderServiceImpl(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	}

	@Override
	@Transactional
	public void createOrder(OrderDTO orderDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Order order = modelMapper.map(orderDTO, Order.class);
		orderRepository.save(order);

	}

	@Override
	@Transactional
	public OrderDTO getOrder(String memberId) {
		Order order = orderRepository.findByMemberId(memberId);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

		return orderDTO;
	}


}
