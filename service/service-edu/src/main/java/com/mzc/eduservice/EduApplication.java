package com.mzc.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MaZhiCheng
 * @date 2020/11/13 - 22:06
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@EnableFeignClients//Feig服务调用
@SpringBootApplication
@ComponentScan(basePackages = {"com.mzc"})
@EnableDiscoveryClient//nacos注册
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
