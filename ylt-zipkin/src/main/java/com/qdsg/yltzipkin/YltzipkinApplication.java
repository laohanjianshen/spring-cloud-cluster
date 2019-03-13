package com.qdsg.yltzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
@EnableHystrixDashboard
public class YltzipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(YltzipkinApplication.class, args);
    }
}
