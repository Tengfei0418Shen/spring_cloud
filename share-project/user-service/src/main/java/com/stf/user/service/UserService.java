package com.stf.user.service;

import com.stf.user.common.ResponseResult;
import com.stf.user.domain.dto.UserLoginDTO;
import com.stf.user.domain.entity.User;


public interface UserService {
    User findById(Integer id);
    ResponseResult login(UserLoginDTO userLoginDTO);
}
