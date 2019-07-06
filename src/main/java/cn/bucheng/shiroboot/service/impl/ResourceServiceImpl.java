package cn.bucheng.shiroboot.service.impl;

import cn.bucheng.shiroboot.core.constant.ShiroConstant;
import cn.bucheng.shiroboot.core.exception.OtherModuleUseException;
import cn.bucheng.shiroboot.core.exception.ResourceExistException;
import cn.bucheng.shiroboot.mapper.ResourceMapper;
import cn.bucheng.shiroboot.mapper.RoleResourceMapper;
import cn.bucheng.shiroboot.model.dto.ResourceDTO;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import cn.bucheng.shiroboot.model.po.RoleResourcePO;
import cn.bucheng.shiroboot.model.po.UserPO;
import cn.bucheng.shiroboot.model.vo.BaseVO;
import cn.bucheng.shiroboot.service.ResourceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author buchengyin
 * @create 2019/7/6 11:11
 * @describe
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourcePO> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<ResourcePO> loadMen() {
        UserPO user = (UserPO) SecurityUtils.getSubject().getSession().getAttribute("user");
        return resourceMapper.listResource(user.getId(), ShiroConstant.MENU);
    }

    @Override
    public Page<ResourceDTO> listAll(BaseVO vo) {
        Page<ResourceDTO> page = new Page<>(vo.getPageNum() == null ? 0 : vo.getPageNum(), vo.getPageSize() == null ? 10 : vo.getPageSize());
        Integer total = resourceMapper.selectCount(new EntityWrapper<>());
        page.setTotal(total);
        if (total == 0) {
            return page;
        }
        List<ResourceDTO> resourcePOS = resourceMapper.pageListResource(page.getCurrent()-1, page.getSize());
        page.setRecords(resourcePOS);
        return page;
    }

    @Override
    public void save(ResourcePO po) throws Exception {
        Integer rows = resourceMapper.selectCount(new EntityWrapper<ResourcePO>().eq("res_url", po.getResUrl()));
        if (rows > 0) {
            throw new ResourceExistException();
        }
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        resourceMapper.insert(po);
    }

    @Override
    public List<ResourceDTO> listByRoleId(long roleId) {
        return resourceMapper.listResourceByRoleId(roleId);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Integer rows = roleResourceMapper.selectCount(new EntityWrapper<RoleResourcePO>().eq("resource_id", id));
        if(rows>0){
            throw new OtherModuleUseException();
        }
        resourceMapper.deleteById(id);
    }
}
