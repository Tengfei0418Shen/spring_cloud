package com.stf.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;



@Component
@Slf4j
public class DynamicRoutesListener implements Listener {
    @Resource
    private  GatewayService gatewayService;

    @Override
    public Executor getExecutor() {
        log.info("getExecutor");
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        log.info("received routes changes {}",configInfo);
        List<RouteDefinition> definitions = JSON.parseArray(configInfo,RouteDefinition.class);
        gatewayService.updateRoute(definitions);
    }
}
