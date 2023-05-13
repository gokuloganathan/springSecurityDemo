package com.example.springsecuritydemo;

import com.example.springsecuritydemo.Modal.UserInfo;
import com.example.springsecuritydemo.Repo.UserInfoRepo;
import com.example.springsecuritydemo.Service.AuthenticationService;
import com.example.springsecuritydemo.Service.UserInfoUserDetailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemoApplication implements CommandLineRunner {
	@Autowired
	private UserInfoRepo userInfoRepo;

	@Autowired
	private AuthenticationService authenticationService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	//@PostConstruct
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Loading data....");
	/*	authenticationService.addUser(new UserInfo(1,"gokul","gokul@gmail.com","admin123","ROLE_ADMIN"));
		authenticationService.addUser(new UserInfo(2,"user1","user1@gmail.com","user123","ROLE_USER"));
	*/	System.out.println("ready for authentication...");
	}
}
