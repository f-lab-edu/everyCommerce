package com.everycommerce.orderservice.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class OrderDTO {



	private String memberId;

	private Long quantity;

	private String productId;

}
