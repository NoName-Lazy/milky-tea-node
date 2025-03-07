package com.example.milky_tea_node;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.milky_tea_node.mapper")
public class MilkyTeaNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilkyTeaNodeApplication.class, args);
	}

}