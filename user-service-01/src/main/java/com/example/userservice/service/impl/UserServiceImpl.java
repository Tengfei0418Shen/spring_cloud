package com.example.userservice.service.impl;


import com.example.userservice.common.JwtOperator;
import com.example.userservice.common.ResponseResult;
import com.example.userservice.domain.dto.UserLoginDTO;
import com.example.userservice.domain.entity.User;
import com.example.userservice.domain.vo.LoginVo;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.JavaBean;
import java.util.HashMap;
import java.util.Map;

import static com.example.userservice.common.ResultCode.USER_ACCOUNT_ERROR;



@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtOperator jwtOperator;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseResult login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByMobileAndPassword(userLoginDTO.getMobile(), userLoginDTO.getPassword());
        System.out.println(user);
        if (user == null) {
            return ResponseResult.failure(USER_ACCOUNT_ERROR);
        }
//        user.getRoles();
        Map<String,Object> info = new HashMap<>();
        info.put("role",user.getRoles());
        info.put("id",user.getId());
        LoginVo loginVo = new LoginVo();
        String tok = jwtOperator.generateToken(info);
        BeanUtils.copyProperties(user,loginVo,LoginVo.class);
        loginVo.setToken(tok);
        return ResponseResult.success(loginVo);
    }
}
