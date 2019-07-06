package cn.bucheng.shiroboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：yinchong
 * @create ：2019/7/5 19:18
 * @description：角色控制
 * @modified By：
 * @version:
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @RequestMapping
    public String role(){
        return "role/roles";
    }
}
