package com.example.demo.entity;

import java.util.Date;

public class Problem {
    private String name;
    private Integer id;
    //问题
    private String title;
    //选项
    private String content;
    private String contentA;
    private String contentB;
    private String contentC;
    private String contentD;
    //单位
    private String unit;
    private String unitName;
    //答案
    private String answer;
    //类型
    private String problemType;

    private String remark;
    //最近修改时间
    private Date time;

    private Integer score;

    private String selectKey;

    private String type;

    private String adm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentA() {
        return contentA;
    }

    public void setContentA(String contentA) {
        this.contentA = contentA;
    }

    public String getContentB() {
        return contentB;
    }

    public void setContentB(String contentB) {
        this.contentB = contentB;
    }

    public String getContentC() {
        return contentC;
    }

    public void setContentC(String contentC) {
        this.contentC = contentC;
    }

    public String getContentD() {
        return contentD;
    }

    public void setContentD(String contentD) {
        this.contentD = contentD;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        adm = adm;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
