package cn.com.feignconsumerserver.service.serviceimpl;

import cn.com.feignconsumerserver.bean.User;
import cn.com.feignconsumerserver.client.FeignProduceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class IFeignService{

    @Autowired
    private FeignProduceClient client;

    public void getService(){
        client.get();
    }

    public void getInfo(){
        System.out.println("系统开始调用传参数");
       String st = client.getInfo("传递的参数为name","传递的参数为code");
       System.out.println("调用返回来的结果为"+st);
    }

    public void putUser(){
        User user = new User();
        user.setCode("code");
        user.setName("name");
        user.setId(2);
        User ss = client.putUser(user);
        System.out.println(ss.toString());
    }
}
