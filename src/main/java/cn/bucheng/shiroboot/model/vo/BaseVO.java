package cn.bucheng.shiroboot.model.vo;

import java.io.Serializable;

/**
 * @author buchengyin
 * @create 2019/7/6 9:48
 * @describe
 */
public class BaseVO implements Serializable {
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
