package com.example.demo.tenum;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcEnumConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TEnumConverter());
    }

    /**
     * 通用枚举转换器（同时处理 String/Integer/Long 等类型）
     */
}