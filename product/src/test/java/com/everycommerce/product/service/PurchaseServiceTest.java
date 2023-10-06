package com.everycommerce.product.service;

import com.everycommerce.product.domain.Product;
import com.everycommerce.product.repository.ProductRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class PurchaseServiceTest {

	@Autowired
	private PurchaseServiceImpl purchaseService;
	@Autowired
	private ProductRepository productRepository;
	@BeforeEach
	public void setup() {
		Product product = new Product();
		product.setId("1");
		product.setName("Test Product");
		product.setPrice(10000L);
		product.setSort("Electronic");
		product.setSaleStatus(true);
		product.setQuantity(100L);

		productRepository.save(product);
	}

	@AfterEach
	public void after(){
		productRepository.deleteAll();
	}
	@Test
	void test(){
		purchaseService.purchase("1",1);
		Product product = productRepository.findById("1").orElseThrow();
		assertEquals(0,product.getQuantity());

	}
}
