package com.example.validation.entity;

import com.example.validation.annotation.Validation;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-09 10:57
 */
@Validation
public class User {
    private long id;
    @NotNull
    private String name;
    @DecimalMin(value = "1000")
    private long number;
    @Size(min = 1, max = 10)
    private String remark;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getNumber() {
        return this.number;
    }

    public String getRemark() {
        return this.remark;
    }
}
