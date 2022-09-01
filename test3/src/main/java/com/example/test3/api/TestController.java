package com.example.test3.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @program: test3
 * @description:
 * @author: ShenTF
 * @create: 2022-08-30 19:25:53
 **/
@RestController
public class TestController {
    private final String MTH_URL = "http://33091f9t08.zicp.fun:21469/api/books";
    private final String CHL_URL = "http://yucvsa.natappfree.cc/api/getbook?bookName=aaa&number=123";
    private final String SYK_URL = "http://127.0.0.1:8085/test1";

    @GetMapping("/mth")
    public String getApi() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse execute = client.execute(new HttpGet(MTH_URL ));
        System.out.println("马天浩API返回结果："+ EntityUtils.toString(execute.getEntity()));
        return "success";
    }

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/chl")
    public String getApi1() throws IOException {
        ResponseEntity<String> entity = restTemplate.getForEntity(CHL_URL , String.class);
        System.out.println("曹红亮API返回结果："+ entity.getBody());
        return "success";
    }

    private WebClient webClient = WebClient.builder().baseUrl(SYK_URL).build();
    @GetMapping("/syk")
    public String getApi2() throws IOException {
        Mono<String> stringMono = webClient.get().uri("").retrieve().bodyToMono(String.class);
        System.out.print("沈永康API返回结果：");
        stringMono.subscribe(System.out::println);
        return "success";
    }

}
