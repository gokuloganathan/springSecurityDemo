package com.example.springsecuritydemo.Service;

import com.example.springsecuritydemo.Config.UserInfoUserDetails;
import com.example.springsecuritydemo.Modal.UserInfo;
import com.example.springsecuritydemo.Repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailService implements UserDetailsService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo =  userInfoRepo.findByName(username);

        return userInfo.map(UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
    }
}
