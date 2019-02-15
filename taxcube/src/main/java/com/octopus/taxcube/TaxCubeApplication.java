package com.octopus.taxcube;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @SpringbootApplication 相当于 @Configuration, @EnableAutoConfiguration 和 @ComponentScan 并具有他们的默认属性值
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.octopus.taxcube.eds.dao"})
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class TaxCubeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxCubeApplication.class, args);
    }
}
