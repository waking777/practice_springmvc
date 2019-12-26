package cn.itcast.controller;

import cn.itcast.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求参数绑定
 */
@Controller
@RequestMapping("/param")
public class ParamController {


    @RequestMapping("/testParam")
    public String testParam(String username){
        System.out.println("执行了。。。");
        System.out.println(username);
        return "success";
    }


    /**
     * 请求参数绑定吧数据封装到javabean中
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println("执行了。。。");
        System.out.println(account);
        return "success";
    }
}
