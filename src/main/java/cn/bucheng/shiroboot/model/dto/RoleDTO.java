package cn.bucheng.shiroboot.model.dto;

import cn.bucheng.shiroboot.model.po.RolePO;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/6 14:29
 * @describe
 */
public class RoleDTO extends RolePO implements Serializable {
    private Boolean selected;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
