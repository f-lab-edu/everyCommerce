package com.everycommerce.product.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Producer {
	private static final String TOPIC = "product_topic";


	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		this.kafkaTemplate.send(TOPIC, message);
	}
}
