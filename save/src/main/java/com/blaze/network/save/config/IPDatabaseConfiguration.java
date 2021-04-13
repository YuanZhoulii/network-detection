package com.blaze.network.save.config;

import net.ipip.ipdb.City;
import net.ipip.ipdb.District;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 3:50
 */
@Configuration
public class IPDatabaseConfiguration {
    @Bean
    public City city() {
        City db=null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ipipfree.ipdb");
        try {
            db = new City(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    @Bean
    public District district() {
        District db=null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ipipfree.ipdb");
        try {
            db = new District(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }
}
