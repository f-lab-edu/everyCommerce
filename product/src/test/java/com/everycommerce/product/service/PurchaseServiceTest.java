/*
package com.everycommerce.product.service;

import com.everycommerce.product.domain.Product;
import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.repository.ProductRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
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
public class PurchaseServiceTest {

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void test() throws InterruptedException {
		DecreaseDTO decreaseDTO = new DecreaseDTO();
		decreaseDTO.setCount(1L);
		decreaseDTO.setId("1");
		int threadCount = 100;
		Executor executor = Executors.newCachedThreadPool();//쓰레드풀 재사용
		ExecutorService executorService = Executors.newFixedThreadPool(32); //고정된 쓰레드
		CountDownLatch latch = new CountDownLatch(100);//100 개 끝날때까지 ,,

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {

					purchaseService.purchase(decreaseDTO);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		Product product = productRepository.findById("1").orElseThrow();
		assertEquals(0,product.getQuantity());

	}
}
*/
