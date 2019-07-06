package cn.bucheng.shiroboot.controller;

import cn.bucheng.shiroboot.core.base.ServerResult;
import cn.bucheng.shiroboot.model.vo.RoleVO;
import cn.bucheng.shiroboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String role() {
        return "role/roles";
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(RoleVO vo) {
        return roleService.listAll(vo);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object addRole(String roledesc) {
        try {
            roleService.addRole(roledesc);
        } catch (Exception e) {
            return ServerResult.fail("角色名称已经存在");
        }
        return ServerResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail("正在被其他资源使用");
        }
        return ServerResult.success();
    }

    @RequestMapping("/listRoleByUserId")
    @ResponseBody
    public Object listRoleByUserId(Long userId) {
        return roleService.listRoleByUserId(userId);
    }

    @RequestMapping("/addRoleResource")
    @ResponseBody
    public Object addRoleResource(Long roleId, Long[] resourceIds) {
        roleService.addRoleResource(roleId, resourceIds);
        return ServerResult.success();
    }
}