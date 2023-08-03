package com.atguigu.spzx;

import com.atguigu.spzx.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * projectName: com.atguigu.spzx
 *
 * @author: ppp
 * time: 2023/7/28 19:34
 * description:
 */
@SpringBootApplication
@EnableConfigurationProperties(UserAuthProperties.class)
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
