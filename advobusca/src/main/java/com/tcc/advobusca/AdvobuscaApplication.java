package com.tcc.advobusca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.tcc.advobusca.Entity")
public class AdvobuscaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvobuscaApplication.class, args);
	}

}
