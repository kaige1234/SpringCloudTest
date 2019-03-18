package cn.com.orderserver.controller;

import cn.com.orderserver.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 非参数模式的调用
      * @return
     */
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

    /**
     * 在使用Ribbon进行负载均衡调用时，传递参数需要添加@RequestParam标签
     * @param name
     */
    @RequestMapping(value = "/getVariable",method = RequestMethod.GET)
    public void getVariable(@RequestParam String name){
        System.out.println("传递参数的"+name);
    }

    /**
     * 通过接口传递队对象时，需要使用post接口去传递对象
     * @param user
     */
    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public void getUser(@RequestBody User user){
        System.out.println("dddddd");
        System.out.println(user.toString());
    }

    @RequestMapping(value = "/getUserByReturnUser",method = RequestMethod.POST)
    public void getUserByReturnUser(@RequestBody User user){
        System.out.println("dddddd");
        System.out.println(user.toString());
    }

}
