package com.xx.xchat;

import com.xx.xchat.config.ZkRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XchatNettyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(XchatNettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 向ZK注冊
        new Thread(new ZkRegistry()).start();
    }
}
