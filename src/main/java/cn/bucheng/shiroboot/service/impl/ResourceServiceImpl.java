package cn.bucheng.shiroboot.service.impl;

import cn.bucheng.shiroboot.core.constant.ShiroConstant;
import cn.bucheng.shiroboot.mapper.ResourceMapper;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import cn.bucheng.shiroboot.model.po.UserPO;
import cn.bucheng.shiroboot.service.ResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<ResourcePO> loadMen() {
        UserPO user = (UserPO) SecurityUtils.getSubject().getSession().getAttribute("user");
        return resourceMapper.listResource(user.getId(), ShiroConstant.FIRST);
    }
}
