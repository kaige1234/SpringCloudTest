package cn.com.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GatewayController
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/21 0021 下午 5:53
 * @Version 1.0
 **/

@RestController
public class GatewayController {

    @RequestMapping(value = "/get")
    public void get(){
        System.out.println("测试访问本地网关");
    }
}
