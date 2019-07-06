package cn.bucheng.shiroboot.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;

/**
 * @author buchengyin
 * @create 2019/7/6 21:12
 * @describe web相关工具类
 */
public class WebUtils {

    private static ServletRequestAttributes getAttribute(){
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       return attributes;
    }

    public static void saveCookie(String key,String value){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        getAttribute().getResponse().addCookie(cookie);
    }

    public static String getValueFromCookie(String key){
        Cookie[] cookies = getAttribute().getRequest().getCookies();
        if(null==cookies||cookies.length==0)
            return null;
        for(Cookie cookie:cookies){
            if(key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }
}
