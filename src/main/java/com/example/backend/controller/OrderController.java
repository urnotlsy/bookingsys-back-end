package com.example.backend.controller;

import com.example.backend.entity.Order;
import com.example.backend.entity.OrderListInfo;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    //新增order
    @PostMapping("/insert")
    public Integer insert(@RequestBody Order order){

        return orderService.insert(order);
    }

    //查询order
    @GetMapping
    public List<Map<String,Object>> findAll(){

        return orderService.findAll();
    }

    //根据room_id查询
    @GetMapping("/getByRoom")
    public List<Map<String,Object>> getOrderByRoom(@RequestParam Integer room_id){
        return orderService.getOrderByRoom(room_id);
    }

    //更新会议记录recor
    @PostMapping("/record")
    public int updateRecord(@RequestParam Integer order_id,@RequestParam String record){
        return orderMapper.updateRecord(order_id,record);
    }
}
