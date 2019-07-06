package cn.bucheng.shiroboot.model.vo;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/6 9:49
 * @describe
 */
public class UserVO extends BaseVO implements Serializable {
    private String userName;
    private Long id;
    private Boolean enable;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
