package cn.bucheng.shiroboot.mapper;

import cn.bucheng.shiroboot.model.po.RoleResourcePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceMapper extends BaseMapper<RoleResourcePO> {
    int batchSave(@Param("datas")List<RoleResourcePO> datas);
}
