package com.wuyiz.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-05 22:47
 * @description: 启动类
 */
@SpringBootApplication
@MapperScan("com.wuyiz.server.mapper")
public class YebApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(YebApplicaton.class, args);
    }
}
