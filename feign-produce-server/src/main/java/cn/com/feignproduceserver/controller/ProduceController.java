package cn.com.feignproduceserver.controller;

import cn.com.feignproduceserver.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProduceController {

    @RequestMapping(value = "/get")
    public String get(){
        System.out.println("有人来调用我了");
        return "feign-produce-server";
    }

    @RequestMapping(value = "/getInfo")
    public String getInfo(String name,String code){
        log.info("调用成功了"+name +"/////"+code);
        return "调用Produce返回结果";
    }

    @RequestMapping(value = "/putUser", method = RequestMethod.POST)
    public User putUser(@RequestBody User user){
        log.info("调用成功了");
        return user;
    }
}
