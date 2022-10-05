package com.stf.content.common;

import com.stf.content.domain.entity.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: content-service-01
 * @description:
 * @author: ShenTF
 * @create: 2022-10-04 16:12:49
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionErrorHandler {
    @ExceptionHandler(SecurityException.class)

    public ResponseEntity error(SecurityException e){
       log.warn("发生异常////////////");
        return new ResponseEntity<>(
                ErrorEntity.builder()
                        .body(e.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }
}
