package com.everycommerce.orderservice.service;

import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.dto.ProductDTO;
import com.everycommerce.orderservice.vo.RequestProduct;
import com.everycommerce.orderservice.vo.ResponseProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class OrderServiceTest {
	ResponseProduct responseProduct;


	@Autowired
	RestTemplate restTemplate;
	@Autowired
	OrderSerive orderSerive;
	@BeforeEach
	public void setup() {



	}


	@Test
	public void test() throws InterruptedException {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setQuantity(1L);
		orderDTO.setMemberId("1");
		orderDTO.setProductId("1");
		int threadCount = 30;
		Executor executor = Executors.newCachedThreadPool();//쓰레드풀 재사용
		ExecutorService executorService = Executors.newFixedThreadPool(30); //고정된 쓰레드
		CountDownLatch latch = new CountDownLatch(30);//100 개 끝날때까지 ,,

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					System.out.println("test시작");
					orderSerive.createOrder(orderDTO);
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();
/*		RequestProduct product = new RequestProduct();

		product.setId(orderDTO.getProductId());
		String id = "1";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(id,headers);

		String url ="http://127.0.0.1:9091/product-service/api/getProduct/"+product.getId();
		ResponseEntity<ProductDTO> dto = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<ProductDTO>() {
		});
		assertEquals(0,dto.getBody().getQuantity());*/



	}


}
