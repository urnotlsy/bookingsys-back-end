package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
public class Order {
    private Integer order_id;
    private Integer room_id;
    private Integer user_id;
    private String theme;               //会议主题
    private Boolean flag;               //是否涉及意识形态
    private String note;                //预约时填写的备注
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date order_date;
    @JsonFormat(pattern="HH:mm:ss")
    private Time start_time;
    @JsonFormat(pattern="HH:mm:ss")
    private Time end_time;
    private Integer state;                //审核状态
    private String record;               //会议室使用记录
    private Timestamp create_time;       //预约时间
}
