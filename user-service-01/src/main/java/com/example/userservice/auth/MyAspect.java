package com.example.userservice.auth;

import com.example.userservice.common.JwtOperator;
import io.jsonwebtoken.Claims;
import org.apache.coyote.Request;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: user-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-10-04 14:40:42
 **/
@Aspect
@Component
public class MyAspect {
    @Resource
    private JwtOperator jwtOperator;

    @Around("@annotation(com.example.userservice.auth.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        checkLogin();
        return proceedingJoinPoint.proceed();
    }

    private  void checkLogin(){
        try {
            HttpServletRequest request = getRequest();
            String token = request.getHeader("token");
            Boolean aBoolean = jwtOperator.validateToken(token);
            if (!aBoolean){
                throw new RuntimeException("Token不合法");
            }
            Claims claims = jwtOperator.getClaimsFromToken(token);
            request.setAttribute("id",claims.get("id"));
            request.setAttribute("nickname",claims.get("nickname"));
            request.setAttribute("role",claims.get("admin"));
        }catch (Throwable e){
            throw new RuntimeException("Token不合法");
        }


    }

//    @Around("@annotation(com.example.userservice.auth.CheckAuthorization)")
//    public Object checkLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        CheckAuthorization();
//        return proceedingJoinPoint.proceed();
//    }


    HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes1 = (ServletRequestAttributes) requestAttributes;
        assert  requestAttributes1 !=null;
        return requestAttributes1.getRequest();
    }

}
