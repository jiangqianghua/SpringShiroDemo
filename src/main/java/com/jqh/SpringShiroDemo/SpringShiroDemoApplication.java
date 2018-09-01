package com.jqh.SpringShiroDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"com.jqh.SpringShiroDemo.mapper"})
public class SpringShiroDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShiroDemoApplication.class, args);
	}
}
