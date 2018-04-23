package com.silu.web;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WebApplication {
    private static Logger logger = Logger.getLogger(WebApplication.class);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
    	logger.debug("SERVER is Running");
        SpringApplication.run(WebApplication.class, args);
    }

}
