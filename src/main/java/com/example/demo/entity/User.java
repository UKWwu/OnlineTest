package com.example.demo.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String userName;
    private String password;
    private String userType;
    private String userTel;
    private String userEmai;
    private String userTypeName;
    private String userAccount;
    private String selectKey;
    private String remark;
    private Date time;
    private Integer userUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserEmai() {
        return userEmai;
    }

    public void setUserEmai(String userEmai) {
        this.userEmai = userEmai;
    }

    public Integer getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(Integer userUnit) {
        this.userUnit = userUnit;
    }
}
