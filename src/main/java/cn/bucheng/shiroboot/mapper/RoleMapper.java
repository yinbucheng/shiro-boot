package cn.bucheng.shiroboot.mapper;

import cn.bucheng.shiroboot.model.dto.RoleDTO;
import cn.bucheng.shiroboot.model.po.RolePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author buchengyin
 * @create 2019/7/5 22:49
 * @describe
 */
public interface RoleMapper extends BaseMapper<RolePO> {
    RolePO findByName(@Param("name") String name);
    List<RoleDTO> listByUserId(@Param("userId") Long id);
}
