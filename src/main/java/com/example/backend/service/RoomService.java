package com.example.backend.service;

import com.example.backend.entity.Room;
import com.example.backend.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomMapper roomMapper;

    public int insert(Room room){

        return roomMapper.insert(room);
    }
}
