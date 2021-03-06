package cn.com.ribbonconsumer.controller;

import cn.com.ribbonconsumer.bean.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.HystrixThreadPoolKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * 通过调用HystrixCommand 来实现请求的同步执行和异步执行
 * 这里只是对请求同步执行或者是异步执行做一个类
 */
@Slf4j
public class UserCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    private Long id;

    protected UserCommand(RestTemplate restTemplate,Long id) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.restTemplate = restTemplate;
        this.id = id;
    }






    @Override
    protected String run() throws Exception {
        log.info("HystrixCommand实现同步执行");
        User user = new User();
        user.setCode("code");
        user.setName("name");
        user.setId(222);
        return restTemplate.getForObject("http://ORDER-SERVER/index",String.class);

    }


    //----------------------------------缓存的使用------------------------------------------------

    public void flushCache(long id){
        //HystrixRequestCache.getInstance()
    }


    /***
     * 开启请求缓存的方法
     * 只需要实现HystrixCommand 和HystrixObservableCommand 时，
     * 实现其中的getCacheKey()方法来开启缓存
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(12);
    }
}
