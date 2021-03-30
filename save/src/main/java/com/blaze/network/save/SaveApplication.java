package com.blaze.network.save;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blaze.network.save.dao")
public class SaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveApplication.class, args);
    }

}
