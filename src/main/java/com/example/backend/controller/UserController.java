package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //新增user
    @PostMapping("/insert")
    public Integer insert(@RequestBody User user){

        return userService.insert(user);
    }

    //更新user
    @PostMapping("/update")
    public Integer update(@RequestBody User user){

        return userService.update(user);
    }

    //查询user
    @GetMapping
    public List<User> index(){

        return userMapper.findAll();
    }

    //删除user
    @DeleteMapping("/{user_id}")
    public Integer delete(@PathVariable Integer user_id){

        return userMapper.deleteById(user_id);
    }

    //分页查询user
    @GetMapping("/page")
    public Map<String,Object> findByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return userService.findByPage(pageNum,pageSize);
    }

    //重置密码
    @GetMapping("/reset")
    public Integer resetPass(@RequestParam Integer user_id){
        return userMapper.resetPass(user_id);
    }
}
