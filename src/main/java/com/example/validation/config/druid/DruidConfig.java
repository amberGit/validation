package com.example.validation.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-08 22:16
 */
@Configuration
public class DruidConfig {
    @Value("${druid-monitor.white-list}")
    private String whiteList;

    @Value("${druid-monitor.black-list")
    private String blackList;

    @Value("${druid-monitor.username}")
    private String userName;

    @Value("${druid-monitor.password}")
    private String password;

    @Value("${druid-monitor.reset-enable}")
    private String resetEnable;


    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("deny", blackList);
        servletRegistrationBean.addInitParameter("allow", whiteList);
        servletRegistrationBean.addInitParameter("loginUsername", userName);
        servletRegistrationBean.addInitParameter("loginPassword", password);
        servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        filterRegistrationBean.addUrlPatterns("/*");

        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;
    }

}
