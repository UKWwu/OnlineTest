package com.example.demo.entity;

import java.util.Date;

public class UserAndExam {
    private Integer userId;
    private Integer Id;
    private Integer examinationId;
    private Date beginTime;
    private Date endTime;
    private Date continueTime;
    private String userName;
    private Integer util;
    private Integer userAccountId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getContinueTime() {
        return continueTime;
    }

    public void setContinueTime(Date continueTime) {
        this.continueTime = continueTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUtil() {
        return util;
    }

    public void setUtil(Integer util) {
        this.util = util;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
