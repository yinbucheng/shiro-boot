package cn.bucheng.shiroboot.core.base;

import java.io.Serializable;

/**
 * @author ：yinchong
 * @create ：2019/7/5 20:43
 * @description：前端返回交互
 * @modified By：
 * @version:
 */
public class ServerResult implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ServerResult(Integer code, String messge, Object data) {
        this.code = code;
        this.message = messge;
        this.data = data;
    }

    public static ServerResult success(String message){
        return new ServerResult(200,message,null);
    }

    public static ServerResult success(){
        return success("操作成功");
    }

    public static ServerResult fail(String message){
        return new ServerResult(500,message,null);
    }

    public static ServerResult successWithData(Object data){
        return new ServerResult(200,"操作成功",data);
    }
}
