package com.ansid.orderservice;

import com.ansid.orderservice.order.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class OrderServiceApplication {

	@Bean
	CommandLineRunner runner(OrderService orderService) {
		return args -> {
			var created = orderService.createOrder(
					UUID.randomUUID(),
					new BigDecimal("100.50")
			);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}