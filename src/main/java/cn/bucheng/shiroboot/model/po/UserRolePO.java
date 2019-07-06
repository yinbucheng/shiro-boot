package cn.bucheng.shiroboot.model.po;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/5 22:39
 * @describe
 */
@TableName("user_role")
public class UserRolePO extends BasePO implements Serializable {
    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
