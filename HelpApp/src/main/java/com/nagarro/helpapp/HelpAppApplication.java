package com.nagarro.helpapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.nagarro.helpapp.demoentity.LoginInfo;

@SpringBootApplication
public class HelpAppApplication {

	@Bean
	public RestTemplate  getRestTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean
	public LoginInfo  getLoginInfo()
	{
		return new LoginInfo();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelpAppApplication.class, args);
	}

}
