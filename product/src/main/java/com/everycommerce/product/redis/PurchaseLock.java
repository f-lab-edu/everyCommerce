package com.everycommerce.product.redis;

import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.dto.ProductDTO;
import com.everycommerce.product.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class PurchaseLock {

	private RedissonClient redissonClient;

	private PurchaseService purchaseService;

	public PurchaseLock(RedissonClient redissonClient,PurchaseService purchaseService){
		this.purchaseService = purchaseService;
		this.redissonClient = redissonClient;
	}
	public void decrease(DecreaseDTO decreaseDTO){
		RLock lock = redissonClient.getLock(decreaseDTO.getId());
		boolean available = false;
		try {
			available = lock.tryLock(10, 1, TimeUnit.SECONDS);

			if(!available){
				log.error("lock획득 실패");
				return;
			}
			purchaseService.purchase2(decreaseDTO);

		}catch (InterruptedException e){
			throw new RuntimeException(e);
		}finally {
			if(available){
				lock.unlock();

			}
		}
	}
}
