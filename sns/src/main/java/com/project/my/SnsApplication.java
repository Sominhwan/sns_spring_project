package com.project.my;

import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SnsApplication {
	@Bean
	public HttpSessionListener httpSessionListener(){
	  return new SessionListener();
  
   }
	public static void main(String[] args) {
		SpringApplication.run(SnsApplication.class, args);
	}

}
