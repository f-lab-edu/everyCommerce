package com.everycommerce.product.controller;

import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.dto.ProductDTO;
import com.everycommerce.product.kafka.Producer;
import com.everycommerce.product.redis.PurchaseLock;
import com.everycommerce.product.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
@Slf4j
public class ProductController {

	private final Producer producer;
	private final PurchaseService purchaseService;
	private PurchaseLock lock;


	public ProductController(PurchaseService purchaseService, PurchaseLock lock, Producer producer) {
		this.purchaseService = purchaseService;
		this.lock = lock;
		this.producer = producer;
	}

	/* 물건에 대한 정보를 등록 조회 수정 삭제하는 service이다.
	 * 구매는 따로 필요함.
	 * member과 연동이필요하다.
	 * 특정 물건(sort 추가 필요)에 대해선 자동으로 재고를 채우는 로직을 만들것.
	 * */
	@RequestMapping("/")
	public String home() {
		return "forward:/hello.html";
	}

	@GetMapping("/test")
	public String test() {
		return "성공";
	}

	/**
	 * 물건 생성하기
	 *
	 * @param productDTO
	 * @return
	 */
	@PostMapping("/api/newProduct")
	public String createProduct(@RequestBody ProductDTO productDTO) {

		purchaseService.createProduct(productDTO);

		return "성공";
	}

	/**
	 * 특정 물건 보여주기
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/api/getProduct/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String id) {

		ProductDTO productDTO = purchaseService.getProduct(id);

		return ResponseEntity.status(HttpStatus.OK).body(productDTO);

	}

	/**
	 * 전체물건
	 *
	 * @return
	 */

	@GetMapping("/api/getProducts")
	public ResponseEntity<List<ProductDTO>> getProducts() {


		List<ProductDTO> productDTOList = purchaseService.getProducts();

		return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
	}

	/**
	 * 물건 재고 줄이기
	 */
	@PostMapping("/api/decrease")
	public void purchase(@RequestBody DecreaseDTO decreaseDTO) throws InterruptedException {
		log.info("재고줄이기 시작");
		purchaseService.purchase(decreaseDTO);
	}

	@PostMapping("/publish")
	public void sendMessageToKafkaTopic(@RequestBody String message) {
		this.producer.sendMessage(message);
	}


}
