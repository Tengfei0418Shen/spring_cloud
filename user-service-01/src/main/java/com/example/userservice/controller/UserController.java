package com.example.userservice.controller;

import com.example.userservice.auth.CheckLogin;
import com.example.userservice.common.ResponseResult;
import com.example.userservice.domain.dto.UserLoginDTO;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @CheckLogin
    @GetMapping("{id}")
//    @SentinelResource(value = "getUser", blockHandler = "getUserBlock")
    public ResponseResult getUserById(@PathVariable Integer id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
//        return ResponseResult.success(userService.findById(id));
        return ResponseResult.success(userService.findById(id));
    }


    @PostMapping("/login")
    public ResponseResult getUserById(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
}
