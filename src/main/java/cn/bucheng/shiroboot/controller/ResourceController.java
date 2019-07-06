package cn.bucheng.shiroboot.controller;

import cn.bucheng.shiroboot.core.base.ServerResult;
import cn.bucheng.shiroboot.model.dto.ResourceDTO;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import cn.bucheng.shiroboot.model.vo.BaseVO;
import cn.bucheng.shiroboot.service.ResourceService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public Object loadMenu() {
        Object data = resourceService.loadMen();
        return ServerResult.successWithData(data);
    }

    @RequestMapping
    public String resource() {
        return "resources/resources";
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(BaseVO vo) {
        Page<ResourceDTO> resourceDTOPage = resourceService.listAll(vo);
        return resourceDTOPage;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(ResourcePO po) {
        try {
            resourceService.save(po);
        } catch (Exception e) {
            return ServerResult.fail("url地址已经被使用过了");
        }
        return ServerResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        resourceService.deleteById(id);
        return ServerResult.success();
    }

    @RequestMapping("/listResourceByRoleId")
    @ResponseBody
    public Object listResourceByRoleId(Long rid){
        List<ResourceDTO> resourceDTOS = resourceService.listByRoleId(rid);
        return ServerResult.successWithData(resourceDTOS);
    }
}
