package cn.bucheng.shiroboot.model.dto;

import cn.bucheng.shiroboot.model.po.ResourcePO;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/6 13:33
 * @describe
 */
public class ResourceDTO extends ResourcePO implements Serializable {
    private String parentName;
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
