package cn.bucheng.shiroboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：yinchong
 * @create ：2019/7/5 19:17
 * @description：主界面
 * @modified By：
 * @version:
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/403")
    public String unAuth(){
        return "403";
    }
}
