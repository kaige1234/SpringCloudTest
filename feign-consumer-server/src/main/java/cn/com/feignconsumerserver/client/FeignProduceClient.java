package cn.com.feignconsumerserver.client;

import cn.com.feignconsumerserver.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "feign-produce-server",url = "http://localhost:9092")
public interface FeignProduceClient {

    @RequestMapping(value = "/get",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String get();

    /**
     * 在接口调用中如果要传递参数需要使用@RequestParam 标签进行标记，否则会报错的
     * @param name
     * @param code
     * @return
     */

    @RequestMapping(value = "/getInfo",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getInfo(@RequestParam(value = "name")  String name, @RequestParam(value = "code") String code);

    /**
     * 服务之间在调用的时候，如果传递的参数是对象，那么在传值时不需要使用@RequestBody，
     * 接收数据时也需要使用@RequestBody标签
     * @param user
     * @return
     */
    @RequestMapping(value = "/putUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User putUser( User user);

}
