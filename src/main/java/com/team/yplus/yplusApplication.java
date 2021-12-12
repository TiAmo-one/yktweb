package com.team.yplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.team.yplus.mapper")
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class yplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(yplusApplication.class, args);
    }

}
