package com.blaze.network.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.blaze.network.user.feign")
@MapperScan("com.blaze.network.user.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
        //关闭启动界面的图案
//        SpringApplication application=new SpringApplication(UserApplication.class);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
