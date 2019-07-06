package cn.bucheng.shiroboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author buchengyin
 * @create 2019/7/6 16:35
 * @describe 测试用
 */
@Controller
public class OtherController {

    @RequestMapping("/test")
    public String index(){
        return "index";
    }
}
