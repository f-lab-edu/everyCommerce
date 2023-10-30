package com.everycommerce.product.domain;

import com.everycommerce.product.exception.InsufficientQuantitiyException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@DynamicUpdate /*모든 컬럼중에 변경된 컬럼만 update하는 기능*/
@Getter
@Setter
@Slf4j
public class Product {

	/*
	* TODO: 자동생성으로 바꾸기
	* */
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
	/*
	* TODO: 생성시에 자동등록하게 바꾸기
	* */
	@Column
	private LocalDateTime createdDate;

	/**
	 * 남은수량
	 */
	@Column
	private Long quantity;




	public void decrease(Long quantity){
		if (this.quantity - quantity < 0) {
			throw new InsufficientQuantitiyException("there are any have quantity");
		}
		log.info("재고줄이기 decrease");
		this.quantity -= quantity;
	}
}
