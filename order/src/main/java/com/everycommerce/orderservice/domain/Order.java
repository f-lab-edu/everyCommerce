package com.everycommerce.orderservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
@DynamicUpdate /*모든 컬럼중에 변경된 컬럼만 update하는 기능*/
@Getter
@Setter
public class Order {

	/*
	* 주문,,목록 ,,, 주문  product랑 같이 해야되는디 ;
	* */
	@Id
	@Column(name = "orderId")
	private Long id;

	@Column
	private String memberId;

	@Column
	private Long quantity;

	@Column
	private String productId;

	/*
	* TODO: create시간에 자동생성되게
	* */
	@Column
	private LocalDateTime orderTime;




}
