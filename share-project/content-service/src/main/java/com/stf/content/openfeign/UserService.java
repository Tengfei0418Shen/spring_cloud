package com.stf.content.openfeign;

import com.stf.content.common.ResponseResult;
import com.stf.content.openfeign.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "user-service",path = "/user",fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("{id}")
    ResponseResult getUser(@PathVariable(value = "id") Integer id);
}
