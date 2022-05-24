package com.example.backend.mapper;

import com.example.backend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * from user")
    List<User> findAll();

    @Insert("INSERT into user(user_id,name,phone,role) " +
            "VALUES (#{user_id},#{name},#{phone},#{role})")
    int insert(User user);

    int update(User user);
    @Delete("delete from user where user_id = #{user_id}")
    Integer deleteById(@Param("user_id") Integer user_id);

    //分页查找用户
    @Select("select * from user limit #{pageNum},#{pageSize}")
    List<User> selectByPage(Integer pageNum, Integer pageSize);

    @Select("select count(*) from user")
    Integer selectTotal();

    @Update("UPDATE user set password=\"123456\" where user_id=#{user_id};")
    Integer resetPass(Integer user_id);
    @Select("select * from user where user_id=#{user_id}")
    User getById(Integer user_id);
}
