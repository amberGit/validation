package com.example.validation.entity;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-09 10:57
 */
public class User {
    private long id;
    private String name;
    private long number;
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
