package com.everycommerce.orderservice.service;

import org.junit.jupiter.api.Tag;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Tag("embedded-kafka-test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@EmbeddedKafka
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.location=classpath:application.yaml")
public class OrderServiceKafakTest {
/*
	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker = new EmbeddedKafkaBroker(
			1, true,1,"S"
	)*/

}
