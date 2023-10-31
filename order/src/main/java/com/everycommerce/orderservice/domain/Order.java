package com.everycommerce.orderservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
@DynamicUpdate /*모든 컬럼중에 변경된 컬럼만 update하는 기능*/
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)//인스턴스를 생성하거나 수정할때 감지하여 자동으로 일시저장할수있도록하는 어노테이션
public class Order {

	/*
	* 주문,,목록 ,,, 주문  product랑 같이 해야되는디 ;
	* */
	@Id
	@Column(name = "orderId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String memberId;

	@Column
	private Long quantity;

	@Column
	private String productId;

	@Column(updatable = false)//한번생성시 변경불가
	@CreatedDate //인스턴스가 생성되는거을 감지하여 일자를 저장하는 어노테이션
	private LocalDateTime orderTime;


}
