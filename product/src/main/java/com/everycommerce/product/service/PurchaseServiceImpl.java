package com.everycommerce.product.service;

import com.everycommerce.product.domain.Product;
import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.dto.ProductDTO;
import com.everycommerce.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.convention.MatchingStrategies;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final ProductRepository productRepository;

	private final KafkaTemplate<String, String> kafkaTemplate;

	private final RedissonClient redissonClient;

	private final ObjectMapper objectMapper = new ObjectMapper();

	public PurchaseServiceImpl(ProductRepository productRepository, RedissonClient redissonClient, KafkaTemplate<String, String> kafkaTemplate) {
		this.productRepository = productRepository;
		this.redissonClient = redissonClient;
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * 구매
	 * 동시성보장
	 * TODO: 카프카로 응답보내기
	 */

	@Override
	public void purchase(DecreaseDTO decreaseDTO) throws InterruptedException {
		RLock lock = redissonClient.getLock(decreaseDTO.getId());

		boolean available = false;
		try {
			log.info("Acquired lock for key {}", decreaseDTO.getId());
			available = lock.tryLock(10, 1, TimeUnit.SECONDS);
			if(!available){
				log.error("lock획득 실패");
				return;
			}
			Optional<Product> product = productRepository.findById(decreaseDTO.getId());
			product.get().decrease(decreaseDTO.getCount());
			productRepository.save(product.get());



			ModelMapper modelMapper = new ModelMapper();
			ProductDTO productDTO = modelMapper.map(product.get(), ProductDTO.class);

			log.error("kafak1");
			sendMassageToKafka(objectMapper.writeValueAsString(productDTO));
			log.error("kafak2");
			//성공시에는 카프카로 product 재고 보내기
		}catch (InterruptedException | JsonProcessingException e){
			sendMassageToKafka(e.getMessage());
		//	throw new RuntimeException(e);
		}finally {
			if(available){
				log.info("unlock1");
				lock.unlock();
				log.info("unlock22");

			}
		}
	}


	@Override
	@Transactional
	public void createProduct(ProductDTO productDTO) {
		/*
		 * TODO: 멤버권한이 필요하다  특정 권한 이상 생성이 가능함
		 * */
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Product product = new Product();
		product = modelMapper.map(productDTO, Product.class);
		productRepository.save(product);
	}

	@Override
	public String updateProduct() {
		return null;
	}

	/**
	 * 특정물건 정보 보여주기
	 *
	 * @param id
	 * @return
	 */

	@Override
	@Transactional
	public ProductDTO getProduct(String id) {
		Optional<Product> product = productRepository.findById(id);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ProductDTO productDTO = modelMapper.map(product.get(), ProductDTO.class);
		return productDTO;
	}

	/**
	 * 물건입고
	 */



	/**
	 * 물건 리스트
	 * @return List<ProductDTO>
	 */
	@Override
	@Transactional
	public List<ProductDTO> getProducts() {

		Iterable<Product> productList = productRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDTO> result = new ArrayList<>();
		productList.forEach(p -> result.add(new ModelMapper().map(p, ProductDTO.class)));

		return result;
	}
	public void sendMassageToKafka(String message) {
		log.error("토픽토픽~~");
		kafkaTemplate.send("product_topic",message);
	}
}
