package com.mzc.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MaZhiCheng
 * @date 2020/11/27 - 20:08
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@SpringBootApplication
@ComponentScan("com.mzc")
@MapperScan("com.mzc.educenter.mapper")
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
