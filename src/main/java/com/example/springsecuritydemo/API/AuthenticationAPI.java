package com.example.springsecuritydemo.API;

import com.example.springsecuritydemo.Modal.UserInfo;
import com.example.springsecuritydemo.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/signUp")
    public String addUser(@RequestBody UserInfo userInfo){
        return authenticationService.addUser(userInfo);
    }
}
