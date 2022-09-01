package com.example.demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo1
 * @description:
 * @author: ShenTF
 * @create: 2022-08-30 15:41:03
 **/

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/hello1")
    public String sayHello1(){
        return "hello哈喽";
    }
}
