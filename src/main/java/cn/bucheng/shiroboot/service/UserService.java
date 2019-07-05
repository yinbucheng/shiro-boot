package cn.bucheng.shiroboot.service;

import cn.bucheng.shiroboot.model.po.UserPO;
import com.baomidou.mybatisplus.service.IService;

public interface UserService extends IService<UserPO> {
    void register(String username, String password)throws Exception;
}
