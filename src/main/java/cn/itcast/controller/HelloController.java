package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器类
 */
@Controller
@RequestMapping(path = "/hello1")
public class HelloController {

    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("Hello SpringMVC");
        return "success";
    }

    @RequestMapping(path = "/test")
    public String testRequest(){
        System.out.println("testRequest");
        return "success";
    }

}
