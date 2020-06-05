package net.vv2.config;

import net.vv2.personal.finance.web.Interceptor.PersonalFinanceceptor;
import net.vv2.system.Interceptor.MyAdminInterceptor;
import net.vv2.system.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 配置拦截器
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-10 15:21
 **/

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/baby/**");//添加拦截器并配置拦截请求
        registry.addInterceptor(new MyAdminInterceptor()).addPathPatterns("/admin/**");//后台管理权限拦截判断
        registry.addInterceptor(new PersonalFinanceceptor()).addPathPatterns("/PersonalFinance/**");//添加拦截器并配置拦截请求

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        //registry.addViewController("/atguigu").setViewName("success");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/allDetailReport.html").setViewName("login");
            }
        };
        return adapter;
    }
}
