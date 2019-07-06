package cn.bucheng.shiroboot.mapper;

import cn.bucheng.shiroboot.model.po.ResourcePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends BaseMapper<ResourcePO> {
    List<ResourcePO> listResource(@Param("userId") long id, @Param("type") int type);
}
