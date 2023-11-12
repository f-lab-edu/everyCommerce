package com.everycommerce.orderservice.repository;

import com.everycommerce.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

	Order findByMemberId(String id);
	Order findByProductId(String id);

}
