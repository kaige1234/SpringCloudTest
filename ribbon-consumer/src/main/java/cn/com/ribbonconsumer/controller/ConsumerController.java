package cn.com.ribbonconsumer.controller;

import cn.com.ribbonconsumer.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.exception.FallbackDefinitionException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName ConsumerController
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/15 0015 下午 4:44
 * @Version 1.0
 **/
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;
//-----------------------------------------------------------------------
    /**
     *通过@HystrixCommand(fallbackMethod = "helloFallback")方法来回调函数
     * @return
     * 降级。执行过程中出现错误、超时、线程池拒绝、断路器熔断等情况时，
     * 执行getFallBack()方法内的逻辑
     */
   @RequestMapping("/returnFetion")
    @HystrixCommand(fallbackMethod = "helloFallback")
    public void returnFetion(){
       restTemplate.getForObject("http://ORDER-SERVER/index",String.class);
       // restTemplate.getForEntity("http://ORDER-SERVER/index",String.class);

    }

    /**
     * 熔断器的回调函数
     *
     * 正常服务在调用的时候当请求的服务挂掉后该服务会报错。那么配置了该回调函数
     * 在服务调用的时候如果被调用的服务调用失败该函数将会被调用。
     */
    public void helloFallback(){
        System.out.println("这里是熔断器的回调函数");
    }

//---------------------------------------服务降级的异常处理-------------------------------------



    @HystrixCommand(fallbackMethod ="getThrowable" )
    @RequestMapping(value = "/getUserById")
    public Long getUserById(Long id){
         restTemplate.getForObject("http://ORDER-SERVER/111",String.class);
        return 1L;
    }

    /***
     *
     * @param id
     * @param throwable
     * @return
     */
    public Long getThrowable(Long id,Throwable throwable){
        System.out.println("现在是调用getThrowable出现了异常:"+throwable.getMessage());
        return 2L;
    }

    public Long getId(Long id){
        System.out.println("现在是调用getId出现了异常");
        return 2L;
    }

//---------------------------------------------------------------------------------------------------------------





    @RequestMapping("/ribbon-consumer")
    public void getConsumer(){
         restTemplate.getForEntity("http://ORDER-SERVER/index",String.class).getBody();
    }

    @RequestMapping(value = "/getVariable")
    public String getVariable(){
        System.out.println("传递参数");
        restTemplate.getForEntity("http://ORDER-SERVER/getVariable?name={1}",String.class,"sunkai").getBody();
        return "操作成功";
    }

    @RequestMapping(value = "/getUser")
    public String getUser(){
        System.out.println("传递参数");
        User user = new User();
        user.setCode("code");
        user.setName("name");
        user.setId(222);
        restTemplate.postForEntity("http://ORDER-SERVER/getUser",user,User.class).getBody();
        return "操作成功";
    }


    //-----------------------验证HystrixCommand的同步执行------------------------------------

    @RequestMapping(value = "/checkHystrixCommand")
    public String checkHystrixCommand(){
        new UserCommand(restTemplate,1L).execute();
        return "dddd";
    }




    //-----------------------验证HystrixCommand的异步执行------------------------------------
    @RequestMapping(value = "/sycHystrixCommand")
    public String sycHystrixCommand() throws ExecutionException, InterruptedException {
        Future<String> future = new UserCommand(restTemplate,1L).queue();
        //用来获取返回的数据；
        Thread.sleep(5000);
        future.get();
        return "dddd";
    }
}
