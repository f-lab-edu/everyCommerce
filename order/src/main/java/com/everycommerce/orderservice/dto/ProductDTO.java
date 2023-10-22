package com.everycommerce.orderservice.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private String id;
	private String name;
	private Long price;
	private String sort;
	private Long quantity;
}
