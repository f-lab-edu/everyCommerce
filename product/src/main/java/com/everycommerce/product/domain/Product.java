package com.everycommerce.product.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "product")
@DynamicUpdate
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
	private Integer price;

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

}
