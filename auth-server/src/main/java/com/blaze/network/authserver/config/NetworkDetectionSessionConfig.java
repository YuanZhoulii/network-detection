package com.blaze.network.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class NetworkDetectionSessionConfig {

    //session使用JSON序列化对象到redis中
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    //修改cookie
//    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        //第一次使用session，命令浏览器保存NETWORKSESSIONID这个cookie
        //以后浏览器访问哪个网站就带上那个网站的cookie
        serializer.setCookieName("NETWORKSESSIONID");
        //cookie默认作用域是当前域，不包括子域
        //子域之间：gulimall.com auth.gulimall.com order.gulimall.com
        //指定域名，即使是子域保存的cookie，父域也能使用，解决子域名共享session问题
        serializer.setDomainName("detection.com");
        return serializer;
    }
}