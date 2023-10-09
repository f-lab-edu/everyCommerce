package com.everycommerce.product.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@DynamicUpdate /*모든 컬럼중에 변경된 컬럼만 update하는 기능*/
@Getter
@Setter
public class Product {

	@Id
	@Column(name = "productId")
	private String id;

	/**
	 * 물건이름
	 */
	@Column
	private String name;

	/**
	 * 물건가격
	 */
	@Column
	private Long price;

	/**
	 * 물건종류
	 */
	@Column
	private String sort;

	/**
	 * 판매여부
	 */
	@Column
	private boolean saleStatus;

	/**
	 * 등록일자
	 */
	@Column
	private LocalDateTime createdDate;

	/**
	 * 남은수량
	 */
	@Column
	private Long quantity;


	public void decrease(Long quantity){
		if (this.quantity - quantity < 0) {
			throw new RuntimeException("foo");
		}

		this.quantity -= quantity;
	}
}
