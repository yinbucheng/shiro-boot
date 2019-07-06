package cn.bucheng.shiroboot.controller;

import cn.bucheng.shiroboot.core.base.ServerResult;
import cn.bucheng.shiroboot.core.exception.AccountExistException;
import cn.bucheng.shiroboot.model.vo.UserVO;
import cn.bucheng.shiroboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private UserService userService;

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

    @RequestMapping("/register")
    @ResponseBody
    public Object register(String username, String nickname, String password) {
        try {
            userService.register(username, nickname, password);
        } catch (AccountExistException e) {
            return ServerResult.fail("username exist");
        } catch (Exception e) {
            return ServerResult.fail("system error");
        }
        return ServerResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteUser(Long id) {
        userService.deleteUser(id);
        return ServerResult.success();
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(UserVO userVO) {
        return userService.listAll(userVO);
    }

    @RequestMapping("/addUserRole")
    @ResponseBody
    public Object addUserRole(Long userid,Long[] roleid){
        try {
            userService.addUserRole(userid,roleid);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail("已经存在");
        }
        return ServerResult.success();
    }


}
