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

	@Column
	private String name;
}
