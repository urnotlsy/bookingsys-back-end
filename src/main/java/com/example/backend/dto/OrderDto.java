package com.example.backend.dto;

import com.example.backend.entity.Room;
import com.example.backend.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

@Data
public class OrderDto {
    private Integer order_id;
    private Integer room_id;
    private Integer user_id;
    private String theme;               //会议主题
    private Boolean flag;               //是否涉及意识形态
    private String note;                //预约时填写的备注
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date order_date;
    @JsonFormat(pattern="HH:mm:ss")
    private Time start_time;
    @JsonFormat(pattern="HH:mm:ss")
    private Time end_time;
    private Integer state;                //审核状态

    private Room room;
    private User user;
}


