package cn.bucheng.shiroboot.service;

import cn.bucheng.shiroboot.model.dto.ResourceDTO;
import cn.bucheng.shiroboot.model.po.ResourcePO;
import cn.bucheng.shiroboot.model.vo.BaseVO;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ResourceService extends IService<ResourcePO> {
    List<ResourcePO> loadMen();
    Page<ResourceDTO> listAll(BaseVO vo);
    void save(ResourcePO po)throws  Exception;
    List<ResourceDTO> listByRoleId(long roleId);
    void delete(Long id)throws Exception;
}
