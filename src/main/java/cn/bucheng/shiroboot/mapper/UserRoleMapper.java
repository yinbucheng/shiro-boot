package cn.bucheng.shiroboot.mapper;

import cn.bucheng.shiroboot.model.po.UserRolePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRolePO> {
    int batchSave(@Param("datas") List<UserRolePO> datas);
}
