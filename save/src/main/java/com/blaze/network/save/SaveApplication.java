package com.blaze.network.save;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.blaze.network.save.feign")
@MapperScan("com.blaze.network.save.dao")
public class SaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveApplication.class, args);
    }

}
