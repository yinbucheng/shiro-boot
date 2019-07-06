package cn.bucheng.shiroboot.model.po;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/5 22:39
 * @describe
 */
@TableName("role_resource")
public class RoleResourcePO  extends BasePO implements Serializable {
    private Long id;
    private Long roleId;
    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
