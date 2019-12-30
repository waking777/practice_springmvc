package cn.itcast.controller;

import cn.itcast.domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testString")
    public String testString(Model model) {
        System.out.println("testString执行了");
        //模拟从数据库中查询中User对象
        User user = new User();
        user.setUsername("zhoudongyu");
        user.setPassword("123");
        user.setAge(18);
        //model对象
        model.addAttribute("user", user);
        return "success";
    }

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid执行了");
        //转发,一次请求
        request.getRequestDispatcher("//WEB-INF/pages/success.jsp").forward(request, response);

        //重定向
        response.sendRedirect(request.getContextPath() + "index.jsp");

        //解决中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //直接进行响应
        response.getWriter().print("hello");
        return;
    }


    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUsername("zhoudongyu");
        user.setPassword("999");
        user.setAge(18);

        mv.addObject("user", user);
        mv.setViewName("success");

        return mv;
    }

    /**
     * 模拟异步请求响应
     *
     * @param user
     */
    @RequestMapping("/testAjax")
    public @ResponseBody
    User testAjax(@RequestBody User user) {
        System.out.println("testAjax方法执行了");
        //客户端发了ajax请求，传的是json字符串，后端吧json字符串封装到user对象中
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setAge(18);
        user.setUsername("zhoudongyu");
        user.setPassword("111");
        return user;

    }

    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");

        //使用fileupload组件来完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        //该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //不存在则创建改文件夹
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        //遍历文件项
        for (FileItem item : items) {
            //判断当前对象是否是上传文件项
            if (item.isFormField()) {
                //说明是普通表单项
            } else {
                //说明是上传文件项
                //获取上传文件的名称
                String filename = item.getName();
                //吧文件名称设置为唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //完成文件上传
                item.write(new File(path, filename));
                //删除临时文件
                item.delete();
            }
        }

        return "success";
    }

    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springmvc文件上传");
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        //该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //不存在则创建改文件夹
            file.mkdirs();
        }
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //吧文件名称设置为唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //完成文件上传
        upload.transferTo(new File(path, filename));
        return "success";
    }

}
