package com.everycommerce.orderservice.service;

import com.everycommerce.orderservice.domain.Order;
import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.dto.ProductDTO;
import com.everycommerce.orderservice.repository.OrderRepository;
import com.everycommerce.orderservice.vo.RequestProduct;
import com.everycommerce.orderservice.vo.ResponseProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class OrderServiceImpl implements OrderSerive {

	OrderRepository orderRepository;
	RestTemplate restTemplate;

	public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
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
		orderRepository.save(order);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<RequestProduct> entity = new HttpEntity<>(product, headers);

		/**
		 * TODO: 다른 연결 이용
		 */
		String url ="http://product:9091/product-service/api/decrease";
		//String url = "http://127.0.0.1:9091/product-service/api/decrease";
		ResponseEntity<ProductDTO> dto = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<ProductDTO>() {
		});

		/**
		 * 카프카 이용해서 응답받기
		 */


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


	@KafkaListener(topics = "product_topic", groupId = "productToOrder")
	private void listenToProductStock(String message){
		log.info("Received Kafka message: {}", message);
		try{
			ProductDTO productDTO = convertMessage(message);
		}catch (Exception e){
		 	Order order	= orderRepository.findByProductId(message);
		 	orderRepository.delete(order);
		 	log.error("error kafak ajfkadklf~~~~ 수정하고 ");

		}
		log.info("message from product: {}",message);

	}

	private ProductDTO convertMessage(String message) {
		try {

			return new ObjectMapper().readValue(message, ProductDTO.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to convert message to ProductDTO", e);
		}
	}
}
