package com.nttdata.com.transaction_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TransactionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionMicroserviceApplication.class, args);
	}

}
