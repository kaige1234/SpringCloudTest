package cn.com.orderserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName HelloController
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/15 0015 下午 3:05
 * @Version 1.0
 **/
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        List<String> serviceId = client.getServices();
        if(!CollectionUtils.isEmpty(serviceId)){
            for(String s: serviceId){
                System.out.println("serviceId:"+s);
                List<ServiceInstance> serviceInstanceList =  client.getInstances(s);
                if(!CollectionUtils.isEmpty(serviceInstanceList)){
                    for(ServiceInstance si: serviceInstanceList){
                       logger.info("获取的host:"+si.getHost()+"////"+"port"+si.getPort()+"////uri:"+si.getUri());
                    }
                }
             }
        }
        return "hello";
    }



}
