package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {
    @Bean
    public MessageSource defaultMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public RedisMessageSource redisMessageSource() {
        return new RedisMessageSource(); // 假设 RedisMessageSource 是一个已经存在的类
    }

    @Bean
    public MessageSource messageSource(RedisMessageSource redisMessageSource, MessageSource defaultMessageSource) {
        return new DelegatingRedisMessageSource(redisMessageSource, defaultMessageSource);
    }
}