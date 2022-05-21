package com.example.backend.service;

import com.example.backend.entity.Room;
import com.example.backend.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {
    @Autowired
    private RoomMapper roomMapper;

    public int insert(Room room){

        return roomMapper.insert(room);
    }

    public List<Map<String,Object>> findNumber() {
        List<Room> rooms = roomMapper.findNumber();
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i=0;i<rooms.size();i++){
            Map<String,Object> value = new HashMap<>();
            Room tmp = rooms.get(i);
            value.put("value",tmp.getRoom_id());
            value.put("label",tmp.getRoom_number());
            res.add(value);
        }
        return res;
    }
}
