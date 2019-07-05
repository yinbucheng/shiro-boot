package cn.bucheng.shiroboot.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/5 22:39
 * @describe
 */
@TableName("resource")
@Alias("resource")
public class ResourcePO extends BasePO implements Serializable {
    @TableId
    private Long id;
    private String name;
    private Long parentId;
    private String resUrl;
    private Integer type;
    private Integer sort;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
