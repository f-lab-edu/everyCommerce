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
	@Override
	public void purchase(String id, long count) {


		/*
		 * 아이디로 물건 조회, 몇개남았는지 재고가 없을시에는 발주 (현시스템에서는 그냥 재고 수량 올리기)
		 * 구매는 동시성이 충족되어야 한다.
		 * 재고감소로직 필요함.
		 *
		 */

		//productRepository.save(product);
		int threadCount = 100;
		Executor executor = Executors.newCachedThreadPool();//쓰레드풀 재사용
		ExecutorService executorService = Executors.newFixedThreadPool(10); //고정된 쓰레드
		CountDownLatch latch = new CountDownLatch(100);//100 개 끝날때까지 ,,

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					decrease(id, count);
				} finally {
					latch.countDown();
				}
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	@Transactional
	public void decrease(String id, Long count){
		Product product = productRepository.findByWithPessimisticLock(id);
		product.decrease(count);
		productRepository.save(product);
	}
}
