package com.example.backend.controller;

import com.example.backend.entity.Room;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomService roomService;

    //新增room
    @PostMapping("/insert")
    public Integer insert(@RequestBody Room room){

        return roomService.insert(room);
    }

    //没做更新room的接口

    //查询room
    @GetMapping
    public List<Room> findAll(){

        return roomMapper.findAll();
    }

    //删除room
    @DeleteMapping("/{room_id}")
    public Integer delete(@PathVariable Integer room_id){

        return roomMapper.deleteById(room_id);
    }

    //查询room_id和room_number
    @GetMapping("/getNumber")
    public List<Map<String,Object>> findNumber(){

        return roomService.findNumber();
    }
}
