package com.everycommerce.orderservice.service;

import com.everycommerce.orderservice.domain.Order;
import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.dto.ProductDTO;
import com.everycommerce.orderservice.repository.OrderRepository;
import com.everycommerce.orderservice.vo.RequestProduct;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderSerive{

	OrderRepository orderRepository;
	RestTemplate restTemplate;
	public OrderServiceImpl(OrderRepository orderRepository,	RestTemplate restTemplate){
		this.orderRepository = orderRepository;
		this.restTemplate = restTemplate;
	}

	@Override
	@Transactional
	public void createOrder(OrderDTO orderDTO) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Order order = modelMapper.map(orderDTO, Order.class);


		RequestProduct product = new RequestProduct();
		product.setCount(orderDTO.getQuantity());
		product.setId(orderDTO.getProductId());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<RequestProduct> entity = new HttpEntity<>(product,headers);

		//String url ="http://product:9091/product-service/api/decrease";
		String url ="http://127.0.0.1:9091/product-service/api/decrease";
		ResponseEntity<ProductDTO> dto = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<ProductDTO>() {
		});
		System.out.println(dto.getBody());
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
