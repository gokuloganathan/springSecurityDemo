package com.example.springsecuritydemo.Repo;

import com.example.springsecuritydemo.Modal.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserInfoRepo extends MongoRepository<UserInfo,String> {
    @Query(value = "{name:'?0'}")
    Optional<UserInfo> findByName(String username);
}
