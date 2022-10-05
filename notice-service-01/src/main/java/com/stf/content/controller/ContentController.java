package com.stf.content.controller;

import com.stf.content.common.ResponseResult;
import com.stf.content.common.ResultCode;
import com.stf.content.domain.entity.Share;
import com.stf.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/share")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentController {

    private final ShareService service;




    @GetMapping("{id}")
//    @SentinelResource(value = "getShareById")
    public ResponseResult getShareById(@PathVariable Integer id){
//        String number = service.getNumber(200);
//        log.info(number);
//        if (number.equals("BLOCKED")){
//            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
//        }
        Share share = service.findById(id);
        System.out.println(share);


//        User user1 = JSONObject.parseObject(JSONObject.toJSONString(user.getData()), User.class);
//        ShareDto dto = ShareDto.builder().share(share).avatar(user1.getAvatar()).nickName(user1.getNickname()).build();
//        return ResponseResult.success(number);
        return ResponseResult.success(share);
    }

//    input {
//        beats {
//            port => 5044
//            ssl => true
//            ssl_certificate => "/etc/pki/tls/certs/logstash-beats.crt"
//            ssl_key => "/etc/pki/tls/private/logstash-beats.key"
//        }
//    }


//    input{
//        tcp{
//            port=>5044
//            codec => json_lines
//        }
//    }
//
//    output{
//        elsticsearch{
//            hosts=>["localhost:9200"]
//            index => "helloworld"
//        }
//    }

    @GetMapping("all")
//    @SentinelResource(value = "getShareAll", blockHandler = "getShareAllBlock")
    public ResponseResult getShareAll(){
        String number = service.getNumber(2010);
        log.info(number);
        if (number.equals("BLOCKED")){
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
        return ResponseResult.success(number);
//        return ResponseResult.success(service.findAll());
    }

//    public  ResponseResult getShareAllBlock(BlockException exception) {
//        log.info("接口被限流");
//        log.info(exception.toString());
//        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
//    }

}
