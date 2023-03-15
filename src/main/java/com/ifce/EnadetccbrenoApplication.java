package com.ifce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import db.DB;

@SpringBootApplication
public class EnadetccbrenoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EnadetccbrenoApplication.class, args);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
