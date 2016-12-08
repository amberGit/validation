package com.example.validation.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Map;
import java.util.Properties;

/**
 * @author Amber [johnnyven@outlook.com]
 * @since 2016-12-09 1:17
 */
@Intercepts(@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
))
public class MybatisValidatorPlugin implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // validate
        Object param = invocation.getArgs()[1];
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        return invocation.proceed();
    }

    /**
     * 验证参数
     * @param param
     * @param mappedStatement
     */
    private void validate(Object param, MappedStatement mappedStatement) {
        if (param == null) {

        }

        if (param instanceof Map) {

        }
    }


    @Override
    public Object plugin(Object o) {
        return (o instanceof Executor)? Plugin.wrap(o, this):o;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
