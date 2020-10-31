package com.example.demo.entity;

public class ReceiveEntity {
    //接收对象
    public Object object;
    //页数
    public int page;
    //每页多少
    public int number;
    //用户类型
    public String type;

    //用户名称
    public String userName;

    //所属单位
    public String userUnit;

    public String remark;

    //总条数
    public Integer allNum;

    //目标查询的id
    public Integer targetID;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }

    public Integer getTargetID() {
        return targetID;
    }

    public void setTargetID(Integer targetID) {
        this.targetID = targetID;
    }
}
