package cn.bucheng.shiroboot.service.impl;

import cn.bucheng.shiroboot.core.exception.AccountExistException;
import cn.bucheng.shiroboot.mapper.UserMapper;
import cn.bucheng.shiroboot.model.po.UserPO;
import cn.bucheng.shiroboot.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author buchengyin
 * @create 2019/7/5 22:53
 * @describe
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String username, String password) throws Exception {
        UserPO user = userMapper.findByName(username);
        if(null!=user){
            throw new AccountExistException();
        }
        UserPO userPO = new UserPO();
        userPO.setUserName(username);
        userPO.setPassword(password);
        userPO.setCreateTime(new Date());
        userPO.setUpdateTime(new Date());
        userPO.setEnable(true);
        userMapper.insert(userPO);
    }
}
