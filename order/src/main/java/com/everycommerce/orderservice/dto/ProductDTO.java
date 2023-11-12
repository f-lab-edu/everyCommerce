package com.everycommerce.orderservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
	private String id;

	private String name;

	private Long price;

	private Long quantity;

	private String sort;
}
