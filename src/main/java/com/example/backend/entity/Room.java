package com.example.backend.entity;

import lombok.Data;

@Data
public class Room {
    private Integer room_id;
    private String room_number;
    private Integer capacity;
    private Boolean media;
    private String intro;
}
