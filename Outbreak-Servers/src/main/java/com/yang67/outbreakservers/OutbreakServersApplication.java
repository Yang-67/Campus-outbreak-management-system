package com.yang67.outbreakservers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang67.outbreakservers.mapper")
public class OutbreakServersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutbreakServersApplication.class, args);
    }

}
