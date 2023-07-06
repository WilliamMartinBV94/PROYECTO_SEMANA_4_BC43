package com.nttdata.com.bankingproduct_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BankingProductMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingProductMicroserviceApplication.class, args);
    }

}
