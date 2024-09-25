package com.emazon.api_shopping_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiShoppingCartApplication.class, args);
	}

}
