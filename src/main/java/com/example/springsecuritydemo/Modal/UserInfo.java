package com.example.springsecuritydemo.Modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserInfo {
    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private String role;
}
