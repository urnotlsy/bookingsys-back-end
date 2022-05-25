package com.example.backend.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer user_id;
    private String password;
    private String token;
}
