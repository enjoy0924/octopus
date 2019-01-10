package com.octopus.taxcube;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.octopus.taxcube.eds.dao")
public class FlowableApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowableApplication.class, args);
	}
}
