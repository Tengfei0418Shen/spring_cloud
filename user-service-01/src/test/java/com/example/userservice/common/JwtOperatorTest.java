package com.example.userservice.common;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@EnableSpringConfigured
class JwtOperatorTest {
    @Resource
    JwtOperator jwtOperator;

    @Test
    void a(){
        HashMap<String,Object> info = new HashMap<>();
        info.put("role","admin");
        info.put("nickname","aaaaaa");

        String s = jwtOperator.generateToken(info);
        System.out.println(s);

        String sec = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJuaWNrbmFtZSI6ImFhYWFhYSIsImlhdCI6MTY2NDg3MTkxOCwiZXhwIjoxNjY2MDgxNTE4fQ.7MZnj-zf0MyqqEO3Yp76OrTg7pL_BeBM9m2GHF6HfmU";
        System.out.println(jwtOperator.validateToken(sec));
        Claims claimsFromToken = jwtOperator.getClaimsFromToken(sec);
        System.out.println(claimsFromToken);
        /**
         *
         * eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJuaWNrbmFtZSI6ImFhYWFhYSIsImlhdCI6MTY2NDg3MTkxOCwiZXhwIjoxNjY2MDgxNTE4fQ.7MZnj-zf0MyqqEO3Yp76OrTg7pL_BeBM9m2GHF6HfmU
         */
    }
}