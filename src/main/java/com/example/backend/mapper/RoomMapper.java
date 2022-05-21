package com.example.backend.mapper;

import com.example.backend.entity.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {
    @Select("select * from room")
    List<Room> findAll();

    @Insert("insert  into room(room_number,capacity,media,intro) " +
            "values (#{room_number},#{capacity},#{media},#{intro})")
    int insert(Room room);

    @Delete("delete from room where room_id = #{room_id}")
    Integer deleteById(@Param("room_id") Integer room_id);

    @Select("select room_id,room_number from room")
    List<Room> findNumber();
}
