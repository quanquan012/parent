package com;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *
 * @author lihao
 * @date 2018-12-03 11:59
 */
@MapperScan(basePackages = "com.*.dao")
@EnableFeignClients
@SpringCloudApplication
public class UserClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication .class);
    }
}