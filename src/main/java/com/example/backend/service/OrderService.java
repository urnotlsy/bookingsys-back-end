package com.example.backend.service;

import com.example.backend.entity.Order;
import com.example.backend.entity.OrderListInfo;
import com.example.backend.entity.Room;
import com.example.backend.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Map<String, Object>> getOrderByRoom(Integer room_id) {
        List<Map<String, Object>> res = new ArrayList<>();
        List<OrderListInfo> data=orderMapper.getByRoom(room_id);
        for(int i=0;i< data.size();i++){
            Map<String, Object> value = new HashMap<>();
            OrderListInfo tmp = data.get(i);
            value.put("id",tmp.getOrder_id());
            value.put("title",tmp.getTheme());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String start = dateFormat.format(tmp.getOrder_date())+' '+timeFormat.format(tmp.getStart_time());
            value.put("start",start);
            String end = dateFormat.format(tmp.getOrder_date())+' '+timeFormat.format(tmp.getEnd_time());
            value.put("end",end);
            Map<String, Object> extendedProps = new HashMap<>();
            //extendedProps.put("user_id",tmp.getUser_id());
            extendedProps.put("name",tmp.getUser().getName());
            extendedProps.put("phone",tmp.getUser().getPhone());
            extendedProps.put("room_number",tmp.getRoom().getRoom_number());
            extendedProps.put("state",tmp.getState());
            value.put("extendedProps",extendedProps);
            res.add(value);
        }
        return res;
    }

    public int insert(Order order) {

        return orderMapper.insert(order);
    }

    public List<Map<String,Object>> findAll() {
        List<OrderListInfo> orderListInfo=orderMapper.findAll();
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i=0;i<orderListInfo.size();i++){
            Map<String,Object> value = new HashMap<>();
            OrderListInfo tmp = orderListInfo.get(i);
            value.put("order_id",tmp.getOrder_id());
            value.put("user_id",tmp.getUser_id());
            value.put("room_id",tmp.getRoom_id());
            value.put("name",tmp.getUser().getName());
            value.put("phone",tmp.getUser().getPhone());
            value.put("theme",tmp.getTheme());
            value.put("flag",tmp.getFlag());
            value.put("note",tmp.getNote());
            value.put("room",tmp.getRoom().getRoom_number());
            value.put("order_date",tmp.getOrder_date());
            value.put("start_time",tmp.getStart_time());
            value.put("end_time",tmp.getEnd_time());
            value.put("state",tmp.getState());

            res.add(value);
        }
        return res;
    }
}
