package com.qdsg.ylt.ylt_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.qdsg.ylt"})
@EnableSwagger2
@EnableDiscoveryClient
public class YltUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(YltUserApplication.class, args);
	}
}
