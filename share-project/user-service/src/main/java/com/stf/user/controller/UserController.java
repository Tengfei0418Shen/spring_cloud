package com.stf.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.stf.user.common.ResponseResult;
import com.stf.user.common.ResultCode;
import com.stf.user.domain.dto.UserLoginDTO;
import com.stf.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    @SentinelResource(value = "getUser", blockHandler = "getUserBlock")
    public ResponseResult getUserById(@PathVariable Integer id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
        return ResponseResult.success(userService.findById(id));
    }

    public  ResponseResult getUserBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }

    @PostMapping("/login")
    public ResponseResult getUserById(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
}
