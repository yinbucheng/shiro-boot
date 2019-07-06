package cn.bucheng.shiroboot.service;

import cn.bucheng.shiroboot.model.po.ResourcePO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ResourceService extends IService<ResourcePO> {
    List<ResourcePO> loadMen();
}
