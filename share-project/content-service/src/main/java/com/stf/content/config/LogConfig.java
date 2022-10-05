package com.stf.content.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: share-project
 * @description:
 * @author: ShenTF
 * @create: 2022-09-08 08:38:47
 **/

@Configuration
public class LogConfig {
    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
}
