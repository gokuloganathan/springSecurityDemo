package com.example.springsecuritydemo.API;

import com.example.springsecuritydemo.DTO.AuthRequest;
import com.example.springsecuritydemo.Modal.UserInfo;
import com.example.springsecuritydemo.Service.AuthenticationService;
import com.example.springsecuritydemo.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signUp")
    public String addUser(@RequestBody UserInfo userInfo){
        return authenticationService.addUser(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else{
            throw new UsernameNotFoundException("Invalid user !!");
        }
    }


}
