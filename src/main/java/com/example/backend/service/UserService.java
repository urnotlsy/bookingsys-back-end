package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int insert(User user){

        return userMapper.insert(user);
    }

    public int update(User user){

        return userMapper.update(user);
    }

    public Map<String,Object> findByPage(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<User> userData = userMapper.selectByPage(pageNum,pageSize);
        Integer userTotal = userMapper.selectTotal();
        Map<String,Object> res = new HashMap<>();
        res.put("data",userData);
        res.put("total",userTotal);
        return res;
    }
}
