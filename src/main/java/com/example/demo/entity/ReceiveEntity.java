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
}
