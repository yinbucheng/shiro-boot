package cn.bucheng.shiroboot.model.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/5 22:38
 * @describe
 */
public class RolePO extends BasePO implements Serializable {
    @TableId
    private Long id;
    @TableField
    private String roleDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
