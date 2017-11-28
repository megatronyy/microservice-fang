package com.fang.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceComsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceComsumerApplication.class, args);
	}
}
