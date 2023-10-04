package com.everycommerce.product.repository;

import com.everycommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
