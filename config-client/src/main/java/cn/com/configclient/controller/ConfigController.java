package cn.com.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigController
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/26 0026 下午 3:32
 * @Version 1.0
 **/
@RestController
public class ConfigController {

    @Value("${from}")
     String from;

    @RequestMapping(value = "/getFrom")
    public String getFrom(){
        return this.from;
    }
}
