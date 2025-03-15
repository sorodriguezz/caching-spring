package com.sorz.cachingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachingSpringApplication.class, args);
    }

}
