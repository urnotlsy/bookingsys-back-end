package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {
    private Integer user_id;
    @JsonIgnore
    private String password;
    private String role;
    private String token;
}
