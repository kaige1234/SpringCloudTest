package cn.com.springbootdemo.controller;

import cn.com.springbootdemo.bean.User;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description TODD
 * @Author Administrator
 * @Date 2019/3/14 0014 下午 5:21
 * @Version 1.0
 **/
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String index(){
        return "你很帅";
    }
    @RequestMapping("/get")
    public String get(String name,String age){
        return "你是好人:"+name+"------"+age;
    }

    @RequestMapping("/getUser")
    public String getUser(@RequestBody User user){
        return ""+user.getCode()+"---------"+user.getName();
    }
}
