/*
package com.everycommerce.product.controller;

import com.everycommerce.product.dto.ProductDTO;
import com.everycommerce.product.redis.PurchaseLock;
import com.everycommerce.product.service.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private PurchaseService purchaseService;

	@Mock
	private PurchaseLock lock;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void createProductTest() throws Exception {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId("1");
		productDTO.setQuantity(1L);

		ObjectMapper objectMapper = new ObjectMapper();
		String productJson = objectMapper.writeValueAsString(productDTO);


		mockMvc.perform(post("/product-service/api/newProduct")
				.contentType("application/json")
				.content(productJson))
				.andExpect(status().isOk());

		verify(purchaseService).createProduct(any(ProductDTO.class));
	}
}
*/
