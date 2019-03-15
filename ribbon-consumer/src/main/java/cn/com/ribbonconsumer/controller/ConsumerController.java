package cn.com.ribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ConsumerController
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/15 0015 下午 4:44
 * @Version 1.0
 **/
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/ribbon-consumer")
    public String getConsumer(){
        return restTemplate.getForEntity("http://ORDER-SERVER/index",String.class).getBody();
    }

}
