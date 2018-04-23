package com.silu.eurakaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Euraka 启动
 */
@SpringBootApplication
@EnableEurekaServer
@EnableWebSecurity

public class EurakaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakaserverApplication.class, args);
    }
}
