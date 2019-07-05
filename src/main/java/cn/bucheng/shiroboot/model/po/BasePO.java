package cn.bucheng.shiroboot.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author buchengyin
 * @create 2019/7/5 22:46
 * @describe 最基础的po
 */
public class BasePO  implements Serializable {
    private Date createTime;
    private Date updateTime;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
