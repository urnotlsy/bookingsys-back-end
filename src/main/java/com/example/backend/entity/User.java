package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Integer user_id;
    private String name;
    private String phone;
    @JsonIgnore
    private String password;
    private String role;

}
