package org.example.demomybtis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.example.demomybtis.mapper")
@SpringBootApplication
public class DemoMybtisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybtisApplication.class, args);
    }

}
