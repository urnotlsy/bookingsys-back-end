package com.example.backend.mapper;

import com.example.backend.entity.Order;
import com.example.backend.entity.OrderListInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    int insert(Order order);

    List<OrderListInfo> getByRoom(Integer room_id);
    List<OrderListInfo> findAll();
    @Update("update bookingsys.order set record=#{record} where order_id=#{order_id}")
    int updateRecord(Integer order_id, String record);
}
