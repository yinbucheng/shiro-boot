package cn.bucheng.shiroboot.service.impl;

import cn.bucheng.shiroboot.core.exception.RoleExistException;
import cn.bucheng.shiroboot.mapper.RoleMapper;
import cn.bucheng.shiroboot.mapper.RoleResourceMapper;
import cn.bucheng.shiroboot.model.dto.RoleDTO;
import cn.bucheng.shiroboot.model.po.RolePO;
import cn.bucheng.shiroboot.model.po.RoleResourcePO;
import cn.bucheng.shiroboot.model.vo.RoleVO;
import cn.bucheng.shiroboot.service.RoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author buchengyin
 * @create 2019/7/6 12:12
 * @describe
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public Page<RolePO> listAll(RoleVO roleVO) {
        Page<RolePO> page = new Page<RolePO>(roleVO.getPageNum() == null ? 0 : roleVO.getPageNum(), roleVO.getPageSize() == null ? 10 : roleVO.getPageSize());
        Wrapper<RolePO> wrapper = new EntityWrapper<>();
        if (roleVO.getId() != null) {
            wrapper.eq("id", roleVO.getId());
        }
        if (null != roleVO.getName() && !"".equals(roleVO.getName())) {
            wrapper.like("role_desc", roleVO.getName());
        }
        wrapper.orderDesc(Arrays.asList("update_time"));
        List<RolePO> rolePOS = roleMapper.selectPage(page, wrapper);
        page.setRecords(rolePOS);
        return page;
    }

    @Override
    public void addRole(String name) throws Exception {

        RolePO rolePO = roleMapper.findByName(name);
        if (rolePO != null) {
            throw new RoleExistException();
        }
        rolePO = new RolePO();
        rolePO.setRoleDesc(name);
        rolePO.setCreateTime(new Date());
        rolePO.setUpdateTime(new Date());
        roleMapper.insert(rolePO);
    }

    @Override
    public void deleteRole(long id) {
        roleResourceMapper.delete(new EntityWrapper<RoleResourcePO>().eq("role_id", id));
        roleMapper.deleteById(id);
    }

    @Override
    public List<RoleDTO> listRoleByUserId(Long id) {
        return roleMapper.listByUserId(id);
    }

    @Override
    @Transactional
    public void addRoleResource(Long roleId, Long[] resourceIds) {
        roleResourceMapper.delete(new EntityWrapper<RoleResourcePO>().eq("role_id", roleId));
        if (null == resourceIds) {
            return;
        }
        List<RoleResourcePO> pos = new LinkedList<>();
        for (Long resourceId : resourceIds) {
            Integer rows = roleResourceMapper.selectCount(new EntityWrapper<RoleResourcePO>().eq("role_id", roleId).and().eq("resource_id", resourceId));
            if (rows > 0) {
                continue;
            }
            RoleResourcePO po = new RoleResourcePO();
            po.setResourceId(resourceId);
            po.setRoleId(roleId);
            po.setCreateTime(new Date());
            po.setUpdateTime(new Date());
            pos.add(po);
        }
        if (pos.size() == 0)
            return;
        roleResourceMapper.batchSave(pos);
    }
}
