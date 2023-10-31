package com.everycommerce.product.repository;

import com.everycommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface ProductRepository extends JpaRepository<Product,String> {


	/**
	 * 비관적 락 -> 래디스로 대체
	 * @param id
	 * @return
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Product p where p.id =:id")
	 Product findByWithPessimisticLock(String id);
}
