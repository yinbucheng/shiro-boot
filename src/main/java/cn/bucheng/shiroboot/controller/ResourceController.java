package cn.bucheng.shiroboot.controller;

import cn.bucheng.shiroboot.core.base.ServerResult;
import cn.bucheng.shiroboot.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：yinchong
 * @create ：2019/7/5 20:56
 * @description：权限管理控制器
 * @modified By：
 * @version:
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/loadMenu")
    @ResponseBody
    public Object loadMenu(){
        Object data = resourceService.loadMen();
        return ServerResult.successWithData(data);
    }

    @RequestMapping
    public String resource(){
        return "resources/resources";
    }
}
