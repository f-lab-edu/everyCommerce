package com.everycommerce.product.kafka;

import com.everycommerce.product.service.PurchaseServiceImpl;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class kafkaTest {

	private static final KafkaContainer kafkaContainer = new KafkaContainer();
	private static final GenericContainer redisContainer = new GenericContainer<>("redis:latest")
			.withExposedPorts(6379);
	@BeforeClass
	public static void setUp() {
		kafkaContainer.start();
		redisContainer.start();

		System.setProperty("spring.kafka.bootstrap-servers", kafkaContainer.getBootstrapServers());
		System.setProperty("spring.redis.host", redisContainer.getContainerIpAddress());
		System.setProperty("spring.redis.port", redisContainer.getFirstMappedPort().toString());
	}


	/*@ClassRule
	public static KafkaContainer kafka = new KafkaContainer();*/
	@Test
	public void testSomethingWithKafka() {
		// Kafka 테스트 로직
	}

}
