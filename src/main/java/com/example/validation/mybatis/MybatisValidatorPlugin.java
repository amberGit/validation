package com.example.validation.mybatis;

import com.example.validation.service.ValidatorService;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class MybatisValidatorPlugin implements Interceptor {
    private final ValidatorService validatorService;

    @Autowired
    public MybatisValidatorPlugin(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object param = invocation.getArgs()[1];
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // validate
        validate(param, mappedStatement);
        return invocation.proceed();
    }

    /**
     * 验证参数
     * @param param
     * @param mappedStatement
     */
    private void validate(Object param, MappedStatement mappedStatement) {
        if (param != null && param instanceof Map &&
                (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT ||
                        mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE)
                ) {
            Map<String, Object> paramMap = (Map<String, Object>) param;
            paramMap.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .distinct()
                    .flatMap(it -> validatorService.validate(it).stream())
                    .forEach(it -> {
                        System.out.println("root bean class name: " + it.getRootBeanClass().getName());
                        System.out.println("property path: " + it.getPropertyPath());
                        System.out.println("error message: " + it.getMessage());
                        System.out.println("invalid value:" + it.getInvalidValue());
                    });
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
