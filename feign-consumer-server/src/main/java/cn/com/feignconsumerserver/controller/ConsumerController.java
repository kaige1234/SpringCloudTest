package cn.com.feignconsumerserver.controller;

import cn.com.feignconsumerserver.client.FeignProduceClient;
import cn.com.feignconsumerserver.service.serviceimpl.IFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private IFeignService feignService;

    @RequestMapping(value = "/get")
    public String get(){
        System.out.println("系统调用成功");
        feignService.getService();
        return "ddd";
    }

    @RequestMapping(value = "/getParame")
    public String getParame(){
        feignService.getInfo();
        return "有参数的调用方式";
    }

    @RequestMapping(value = "/putUser")
    public String putUser(){
        feignService.putUser();
        return "传递对象，返回对象";
    }
}
