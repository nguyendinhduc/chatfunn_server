package com.ducnd.chattfunn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableSpringDataWebSupport
@PropertySource("classpath:application.properties")
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@EnableCaching
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
