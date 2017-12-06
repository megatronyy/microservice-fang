package com.fang.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.fang.cloud.mapper")
public class MicroserviceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProviderApplication.class, args);
	}
}
