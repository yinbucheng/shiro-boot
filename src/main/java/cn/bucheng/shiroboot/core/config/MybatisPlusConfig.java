package cn.bucheng.shiroboot.core.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author buchengyin
 * @create 2019/7/6 10:11
 * @describe
 */
@Configuration
@MapperScan(basePackages = "cn.bucheng.shiroboot.mapper")
public class MybatisPlusConfig {

    //添加分页插件拦截
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
