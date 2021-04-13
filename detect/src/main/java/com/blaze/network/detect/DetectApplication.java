package com.blaze.network.detect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.blaze.network.detect.feign")
@SpringBootApplication
public class DetectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetectApplication.class, args);
    }

}
