package com.ifce;
import com.ifce.controller.CorsConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import db.DB;

@SpringBootApplication
@Import(CorsConfiguration.class)
public class EnadetccbrenoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EnadetccbrenoApplication.class, args);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
