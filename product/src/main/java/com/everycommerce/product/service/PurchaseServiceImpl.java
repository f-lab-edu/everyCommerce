package com.everycommerce.product.service;

import com.everycommerce.product.domain.Product;
import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.dto.ProductDTO;
import com.everycommerce.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.convention.MatchingStrategies;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

	private RedissonClient redissonClient;

	public PurchaseServiceImpl(ProductRepository productRepository, RedissonClient redissonClient) {
		this.productRepository = productRepository;
		this.redissonClient = redissonClient;
	}

	/**
	 * 구매
	 * 동시성보장
	 */

	@Override
	@Transactional
	public ProductDTO purchase(DecreaseDTO decreaseDTO) {

		Optional<Product> product = productRepository.findById(decreaseDTO.getId());
		product.get().decrease(decreaseDTO.getCount());
		productRepository.save(product.get());
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		return productDTO;
	}

	@Override
	public void purchase2(DecreaseDTO decreaseDTO) throws InterruptedException {
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
		}catch (InterruptedException e){
			throw new RuntimeException(e);
		}finally {
			if(available){
				log.info("락 지움");
				lock.unlock();

			}
		}
	}



/*
	@Override
	@Transactional
	public Boolean purchase(String id, long count) throws InterruptedException {
		*/
	/*
	 * TODO: order로 빠져야하는 기능.
	 * *//*

		Product product = productRepository.findByWithPessimisticLock(id);
		product.decrease(count);
		productRepository.save(product);
		return true;
	}
*/

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


	//물건 리스트
	@Override
	@Transactional
	public List<ProductDTO> getProducts() {

		Iterable<Product> productList = productRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDTO> result = new ArrayList<>();
		productList.forEach(p -> result.add(new ModelMapper().map(p, ProductDTO.class)));

		return result;
	}
}
