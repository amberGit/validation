package com.example.validation.config.mybatis;

import com.example.validation.mybatis.MybatisValidatorPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-13 19:03
 */
@Configuration
public class MybatisConfig {
    @Autowired
    private MybatisValidatorPlugin validatorPlugin;

    @Bean
    public Interceptor[] mybatisPluginConfig() {
        Interceptor[] plugins = new Interceptor[1];
        plugins[0] = validatorPlugin;
        return plugins;
    }
}
