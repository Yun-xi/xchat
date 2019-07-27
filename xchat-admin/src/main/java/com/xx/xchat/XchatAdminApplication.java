package com.xx.xchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.xx.xchat.dao.mybatis")
@SpringBootApplication
public class XchatAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(XchatAdminApplication.class, args);
    }

}
