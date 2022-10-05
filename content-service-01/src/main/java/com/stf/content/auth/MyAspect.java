package com.stf.content.auth;

import com.stf.content.common.JwtOperator;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

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

    @Around("@annotation(com.stf.content.auth.CheckLogin)")
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
                throw new SecurityException("Token不合法");
            }
            Claims claims = jwtOperator.getClaimsFromToken(token);
            request.setAttribute("role",claims.get("role"));
        }catch (Throwable e){
            throw new SecurityException("Token不合法");
        }
    }

    @Around("@annotation(com.stf.content.auth.CheckAuthorization)")
    private  Object checkAuthorization(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try {
            this.checkLogin();
            HttpServletRequest request = getRequest();
            String role = (String) request.getAttribute("role");
            System.out.println(role+"~~~");
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();
            CheckAuthorization annotation = method.getAnnotation(CheckAuthorization.class);

            System.out.println(role+"~~~"+annotation.value());
            if (!Objects.equals(role,annotation.value())){
                throw new SecurityException("用户无权访问");
            }
        }catch (Throwable e){
            throw new SecurityException("用户无权访问",e);
        }
        return proceedingJoinPoint.proceed();
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
