package com.everycommerce.orderservice.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class OrderDTO {

	private Long id;

	private String memberId;

	private Long quantity;

	private String productId;

	private LocalDateTime orderTime;
}
