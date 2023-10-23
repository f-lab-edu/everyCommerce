package com.everycommerce.product.service;

import com.everycommerce.product.domain.Product;
import com.everycommerce.product.dto.DecreaseDTO;
import com.everycommerce.product.dto.ProductDTO;
import com.everycommerce.product.repository.ProductRepository;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final ProductRepository productRepository;

	public PurchaseServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * 구매
	 * 동시성보장
	 */

	@Override
	public ProductDTO purchase(DecreaseDTO decreaseDTO) {

		Product product = productRepository.findByWithPessimisticLock(decreaseDTO.getId());
		product.decrease(decreaseDTO.getCount());
		productRepository.save(product);
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		return productDTO;
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
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
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
