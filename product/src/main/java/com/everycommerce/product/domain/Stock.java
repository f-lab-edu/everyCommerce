package com.everycommerce.product.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
@DynamicUpdate
@Getter
@Setter
public class Stock {

	@Id
	@Column(name = "stockId")
	private String id;

	/**
	 * 남은수량
	 */
	@Column
	private Integer quantityLeft;

}
