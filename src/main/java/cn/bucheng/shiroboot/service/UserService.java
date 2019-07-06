package cn.bucheng.shiroboot.service;

import cn.bucheng.shiroboot.model.po.UserPO;
import cn.bucheng.shiroboot.model.vo.UserVO;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

public interface UserService extends IService<UserPO> {
    void register(String username,String nickname, String password)throws Exception;
    Page<UserPO> listAll(UserVO userVO);
    void deleteUser(Long id);
    void addUserRole(Long userId,Long[] roleId)throws Exception;
}
