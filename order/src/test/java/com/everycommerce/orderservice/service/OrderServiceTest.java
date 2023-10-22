package com.everycommerce.orderservice.service;

import com.everycommerce.orderservice.dto.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class OrderServiceTest {

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
		int threadCount = 100;
		Executor executor = Executors.newCachedThreadPool();//쓰레드풀 재사용
		ExecutorService executorService = Executors.newFixedThreadPool(32); //고정된 쓰레드
		CountDownLatch latch = new CountDownLatch(100);//100 개 끝날때까지 ,,

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					orderSerive.createOrder(orderDTO);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();



	}


}
