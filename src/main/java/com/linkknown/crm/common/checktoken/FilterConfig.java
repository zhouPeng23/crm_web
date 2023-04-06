package com.linkknown.crm.common.checktoken;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoupeng
 * @date 2023/3/17 13:17
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CheckTokenFilter> registAuth(){
        FilterRegistrationBean<CheckTokenFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        //添加自己的过滤器
        System.out.println("========== 添加自己的过滤器 check-token-filter ==========");
        filterFilterRegistrationBean.setFilter(new CheckTokenFilter());
        filterFilterRegistrationBean.setName("check-token-filter");
        //拦截所有请求
        filterFilterRegistrationBean.addUrlPatterns("/*");
        //优先执行，数越小，优先级越高
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }


}
