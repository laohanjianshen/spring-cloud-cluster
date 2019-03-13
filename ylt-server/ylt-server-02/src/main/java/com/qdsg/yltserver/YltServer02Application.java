package com.qdsg.yltserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YltServer02Application {

    public static void main(String[] args) {
        SpringApplication.run(YltServer02Application.class, args);
    }
}
