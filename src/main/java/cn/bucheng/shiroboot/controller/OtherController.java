package cn.bucheng.shiroboot.controller;

import cn.bucheng.shiroboot.core.base.ServerResult;
import cn.bucheng.shiroboot.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/saveCookie")
    @ResponseBody
    public Object saveCookie(String name,String value){
        WebUtils.saveCookie(name,value);
        return ServerResult.success();
    }

    @RequestMapping("/getCookie")
    @ResponseBody
    public Object getCookie(String name){
        String valueFromCookie = WebUtils.getValueFromCookie(name);
        return ServerResult.successWithData(valueFromCookie);
    }
}
