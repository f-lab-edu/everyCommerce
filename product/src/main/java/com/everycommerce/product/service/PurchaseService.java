package com.everycommerce.product.service;

import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.dto.ProductDTO;

import java.util.List;

public interface PurchaseService {

	/*구매*/
	Boolean purchase(DecreaseDTO decreaseDTO) throws InterruptedException ;

	void createProduct(ProductDTO product);

	String updateProduct();

	ProductDTO getProduct(String id);

	List<ProductDTO> getProducts();



}
