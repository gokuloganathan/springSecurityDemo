package com.example.springsecuritydemo.Repo;

import com.example.springsecuritydemo.Modal.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String username);
}
