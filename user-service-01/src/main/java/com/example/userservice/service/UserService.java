package com.example.userservice.service;


import com.example.userservice.common.ResponseResult;
import com.example.userservice.domain.dto.UserLoginDTO;
import com.example.userservice.domain.entity.User;

public interface UserService {
    User findById(Integer id);
    ResponseResult login(UserLoginDTO userLoginDTO);
}
