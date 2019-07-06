package cn.bucheng.shiroboot.service;

import cn.bucheng.shiroboot.model.dto.RoleDTO;
import cn.bucheng.shiroboot.model.po.RolePO;
import cn.bucheng.shiroboot.model.vo.RoleVO;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface RoleService extends IService<RolePO> {
    Page<RolePO> listAll(RoleVO vo);
    void addRole(String name)throws Exception;
    void deleteRole(long id);
    List<RoleDTO> listRoleByUserId(Long userId);
    void addRoleResource(Long roleId,Long[] resourceIds);
}
