package com.everycommerce.product.service;

import com.everycommerce.product.domain.Product;
import com.everycommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final ProductRepository productRepository;

	public PurchaseServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * 구매
	 * 동시성보장
	 *
	 * @param id
	 * @param count
	 */


	@Transactional
	public void decrease(String id, Long count){
		Product product = productRepository.findByWithPessimisticLock(id);
		product.decrease(count);
		productRepository.save(product);
	}
}
