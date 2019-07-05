package cn.bucheng.shiroboot.controller;

import cn.bucheng.shiroboot.base.ServerResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：yinchong
 * @create ：2019/7/5 16:28
 * @description：
 * @modified By：
 * @version:
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/login")
    @ResponseBody
    public Object login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ServerResult.fail("username is not right");
        } catch (IncorrectCredentialsException e) {
            return ServerResult.fail("password is not right");
        }
        return ServerResult.success("login success");
    }


}
