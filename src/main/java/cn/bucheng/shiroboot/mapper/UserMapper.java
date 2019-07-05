package cn.bucheng.shiroboot.mapper;

import cn.bucheng.shiroboot.model.po.ResourcePO;
import cn.bucheng.shiroboot.model.po.RolePO;
import cn.bucheng.shiroboot.model.po.UserPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<UserPO> {
    UserPO findByName(@Param("name") String name);

    List<ResourcePO> listResourcesByUserName(@Param("username") String username);

    List<RolePO> listRolesByUserName(@Param("username") String username);
}
