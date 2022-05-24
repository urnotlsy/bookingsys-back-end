package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
public class OrderListInfo {
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


