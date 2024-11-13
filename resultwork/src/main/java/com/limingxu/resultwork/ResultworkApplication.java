package com.limingxu.resultwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.limingxu.resultwork.mapper")
public class ResultworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultworkApplication.class, args);
    }

}
