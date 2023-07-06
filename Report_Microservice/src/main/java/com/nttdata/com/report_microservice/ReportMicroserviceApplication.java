package com.nttdata.com.report_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ReportMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportMicroserviceApplication.class, args);
	}

}
