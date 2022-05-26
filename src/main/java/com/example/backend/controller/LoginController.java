package com.example.backend.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.example.backend.dto.UserDto;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public UserDto login(UserDto userDto){
        User target = userMapper.getById(userDto.getUser_id());
        String pass1 = target.getPassword();
        String pass2 = userDto.getPassword();
        if(pass1.equals(pass2)){
            //添加token
            userDto.setToken(JwtUtil.createToken(target.getRole()));
            //权限角色
            userDto.setRole(target.getRole());
            return userDto;
        }
        return null;
    }

    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }
}
