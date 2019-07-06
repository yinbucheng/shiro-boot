package cn.bucheng.shiroboot.service.impl;

import cn.bucheng.shiroboot.core.exception.AccountExistException;
import cn.bucheng.shiroboot.core.exception.RoleExistException;
import cn.bucheng.shiroboot.mapper.UserMapper;
import cn.bucheng.shiroboot.mapper.UserRoleMapper;
import cn.bucheng.shiroboot.model.po.UserPO;
import cn.bucheng.shiroboot.model.po.UserRolePO;
import cn.bucheng.shiroboot.model.vo.UserVO;
import cn.bucheng.shiroboot.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
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

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void register(String username, String nickname, String password) throws Exception {
        UserPO user = userMapper.findByName(username);
        if (null != user) {
            throw new AccountExistException();
        }
        UserPO userPO = new UserPO();
        userPO.setUserName(username);
        userPO.setPassword(password);
        userPO.setNickName(nickname);
        userPO.setCreateTime(new Date());
        userPO.setUpdateTime(new Date());
        userPO.setEnable(true);
        userMapper.insert(userPO);
    }

    @Override
    public Page<UserPO> listAll(UserVO userVO) {
        Page rowPage = new Page(userVO.getPageNum() == null ? 0 : userVO.getPageNum(), userVO.getPageSize() == null ? 10 : userVO.getPageSize());
        Wrapper<UserPO> wrapper = new EntityWrapper<>();
        if (null != userVO.getEnable()) {
            wrapper.eq("enable", userVO.getEnable());
        }
        if (null != userVO.getUserName() && !"".equals(userVO.getUserName())) {
            wrapper.and().like("user_name", userVO.getUserName() + "%");
        }
        if (null != userVO.getId()) {
            wrapper.and("id={0}", userVO.getId());
        }
        List<UserPO> userPOS = userMapper.selectPage(rowPage, wrapper);
        rowPage.setRecords(userPOS);
        return rowPage;
    }

    @Override
    public void deleteUser(Long id) {
        Wrapper<UserRolePO> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", id);
        userRoleMapper.delete(wrapper);
        userMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void addUserRole(Long userId, Long[] roleIds) throws Exception {
        userRoleMapper.delete(new EntityWrapper<UserRolePO>().eq("user_id",userId));
        if(roleIds==null){
            return;
        }
        List<UserRolePO> pos = new LinkedList<>();
        for (Long roleId : roleIds) {
            Integer rows = userRoleMapper.selectCount(new EntityWrapper<UserRolePO>().eq("user_id", userId).and().eq("role_id", roleId));
            if (rows > 0) {
               continue;
            }
            UserRolePO po = new UserRolePO();
            po.setUserId(userId);
            po.setRoleId(roleId);
            po.setCreateTime(new Date());
            po.setUpdateTime(new Date());
            pos.add(po);
        }
        if(pos.size()==0)
            return;
        userRoleMapper.batchSave(pos);
    }
}
