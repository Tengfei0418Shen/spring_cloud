package com.example.userservice.domain.vo;


import com.example.userservice.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: content-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-10-04 21:04:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class LoginVo extends User {
    String token;
}
