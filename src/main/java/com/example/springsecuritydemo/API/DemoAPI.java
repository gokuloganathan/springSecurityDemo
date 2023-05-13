package com.example.springsecuritydemo.API;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/date")
public class DemoAPI {

    @GetMapping("/welcome")
    public String greet(){
        return "welcome to secured spring boot";
    }

    @GetMapping("/todayAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String returnDateForAdmin(){
        return new Date().toString()+" for admin";
    }

    @GetMapping("/todayUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String returnDateForUser(){
        return new Date().toString()+" for user";
    }

}
