package cn.bucheng.shiroboot.model.vo;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/6 12:32
 * @describe
 */
public class RoleVO extends BaseVO implements Serializable {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
