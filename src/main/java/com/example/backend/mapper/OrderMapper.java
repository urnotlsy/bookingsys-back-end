package com.example.backend.mapper;

import com.example.backend.entity.Order;
import com.example.backend.dto.OrderDto;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    int insert(Order order);

    List<OrderDto> getByRoom(Integer room_id);
    List<OrderDto> findAll();
    @Update("update bookingsys.order set record=#{record} where order_id=#{order_id}")
    int updateRecord(Integer order_id, String record);
    @Delete("delete from bookingsys.order where order_id=#{order_id}")
    int cancelOrder(@Param("order_id") Integer order_id);

    @Update("update bookingsys.order set state = #{state} where order_id = #{order_id}")
    int setOrderState(Integer order_id, Integer state);

    @Select("select * from bookingsys.order where order_date=#{order_date} and room_id=#{room_id} and (state = 1 or state  = 2)")
    List<Order> getSameDay(@Param("order_date") Date order_date,@Param("room_id") Integer room_id);
}
