package com.example.springsecuritydemo.Service;

import com.example.springsecuritydemo.Modal.UserInfo;
import com.example.springsecuritydemo.Repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserInfoRepo repo;

     @Autowired
     PasswordEncoder encoder;
     public String addUser(UserInfo userInfo){
         userInfo.setPassword(encoder.encode(userInfo.getPassword()));
         repo.save(userInfo);
         return "User added to system";
     }
}
