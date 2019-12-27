package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString执行了");
        //模拟从数据库中查询中User对象
        User user = new User();
        user.setUsername("zhoudongyu");
        user.setPassword("123");
        user.setAge(18);
        //model对象
        model.addAttribute("user",user);
        return "success";
    }

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid执行了");
        //转发,一次请求
        request.getRequestDispatcher("//WEB-INF/pages/success.jsp").forward(request,response);

        //重定向
        response.sendRedirect(request.getContextPath()+"index.jsp");

        //解决中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //直接进行响应
        response.getWriter().print("hello");
        return;
    }


    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUsername("zhoudongyu");
        user.setPassword("999");
        user.setAge(18);

        mv.addObject("user",user);
        mv.setViewName("success");

        return mv;
    }

    /**
     * 模拟异步请求响应
     * @param user
     */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user) {
        System.out.println("testAjax方法执行了");
        //客户端发了ajax请求，传的是json字符串，后端吧json字符串封装到user对象中
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setAge(18);
        user.setUsername("zhoudongyu");
        user.setPassword("111");
        return user;

    }
}
