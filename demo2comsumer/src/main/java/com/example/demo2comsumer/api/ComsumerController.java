package com.example.demo2comsumer.api;

import com.example.demo2comsumer.config.RestTemplateConfig;
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
 * @program: demo2comsumer
 * @description:
 * @author: ShenTF
 * @create: 2022-08-30 15:51:17
 **/

@RestController
public class ComsumerController {
    @Resource
    private RestTemplate restTemplate;
    private final String SERVICE_URL = "http://localhost:8080/";

private WebClient webClient = WebClient.builder().baseUrl(SERVICE_URL).build();





    @GetMapping("/")
    public String sayHelloToo() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVICE_URL+"hello/");
        HttpGet httpGet1 = new HttpGet(SERVICE_URL+"hello1/");
        CloseableHttpResponse execute = null;
        try {
             execute = httpClient.execute(httpGet);
            // 判断状态码
            if(execute.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
                System.out.println(result);
                execute = httpClient.execute(httpGet1);
                String result2 = EntityUtils.toString(execute.getEntity());
                System.out.println(result2);
            }
        } finally {
            if (execute != null){
                execute.close();
            }
            httpClient.close();
        }


        return "success";
    }


    @GetMapping("/new")
    public String restTemplateTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(SERVICE_URL + "/hello1", String.class);
        System.out.println(forEntity.getBody());
        return forEntity.getBody();

    }



    @GetMapping("/new1")
    public String webClientTest() {
        Mono<String> stringMono = webClient.get().uri("/hello1").retrieve().bodyToMono(String.class);
        stringMono.subscribe(System.out::println);
        return "success";

    }
}
