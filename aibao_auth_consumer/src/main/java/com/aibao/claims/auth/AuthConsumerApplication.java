package com.aibao.claims.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {})
public class AuthConsumerApplication {


    /**
     * 启动入口
     *
     * @param args
     */
    public static void main(String... args) {
        SpringApplication.run(AuthConsumerApplication.class, args);
    }


}
