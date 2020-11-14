package com.mzc.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MaZhiCheng
 * @date 2020/11/13 - 22:08
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */


@Configuration
@MapperScan("com.mzc.eduservice.mapper")
public class EduConfig {

    /**
     2
     * 逻辑删除插件
     3
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
